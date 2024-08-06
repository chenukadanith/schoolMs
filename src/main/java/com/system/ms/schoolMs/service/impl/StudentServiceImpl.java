package com.system.ms.schoolMs.service.impl;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.entity.Credentials;
import com.system.ms.schoolMs.entity.Student;
import com.system.ms.schoolMs.exception.StudentNotFoundException;
import com.system.ms.schoolMs.repo.CredentialsRepo;
import com.system.ms.schoolMs.repo.StudentRepo;
import com.system.ms.schoolMs.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StudentRepo studentRepo;
    private CredentialsRepo credentialsRepo;

    @Autowired
    private void setCredentialsRepo(CredentialsRepo credentialsRepo) {
        this.credentialsRepo = credentialsRepo;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);

        if (student.getId() != null && studentRepo.existsById(student.getId())) {
            throw new DuplicateKeyException("Already Added");
        }

        Student savedStudent = studentRepo.save(student);

        credentialsRepo.save(
                Credentials.builder()
                        .student(savedStudent)
                        .password(studentDto.getPassword())
                        .username(studentDto.getUsername())
                        .build()
        );

        StudentDto savedStudentDto = modelMapper.map(savedStudent, StudentDto.class);
        return savedStudentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        if (studentDto.getId() == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }

        if (!studentRepo.existsById(studentDto.getId())) {
            throw new EntityNotFoundException("Student not found with ID: " + studentDto.getId());
        }

        Student student = modelMapper.map(studentDto, Student.class);
        Student updatedStudent = studentRepo.save(student);
        StudentDto updatedStudentDto = modelMapper.map(updatedStudent, StudentDto.class);
        return updatedStudentDto;
    }

    @Override
    public PaginatedResponseItemDto viewAllStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepo.findAll(pageable);

        List<StudentDto> studentDtos = studentPage.getContent().stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());

        PaginatedResponseItemDto response = new PaginatedResponseItemDto();
        response.setList(studentDtos);
        response.setTotalItems(studentPage.getTotalElements());
        response.setCurrentPage(page);
        response.setTotalPages(studentPage.getTotalPages());

        return response;
    }


    @Override
    public String deleteStudentById(Long studentId) {
        if (studentRepo.existsById(studentId)) {
            studentRepo.deleteById(studentId);
            return "deleted Successfull";
        } else {
            throw new RuntimeException("No customer found that id");
        }
    }

    @Override
    public StudentDto getStudentById(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("no student found that id");
        } else {
            Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("No student found with that ID"));
            return modelMapper.map(student, StudentDto.class);
        }
    }
    //
    public StudentDto checkStudent(String username, String password){
        Optional<Credentials> credentialsOptional = credentialsRepo.findByUsernameAndPassword(username, password);
        if(credentialsOptional.isPresent()){
            return modelMapper.map(credentialsOptional.get().getStudent(), StudentDto.class);
        }
        throw new StudentNotFoundException("Student not found! Invalid credentials.");
    }
    //


}
