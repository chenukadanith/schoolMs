package com.system.ms.schoolMs.service.impl;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.entity.Student;
import com.system.ms.schoolMs.entity.Teacher;
import com.system.ms.schoolMs.repo.TeacherRepo;
import com.system.ms.schoolMs.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TeacherRepo teacherRepo;
    @Override
    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);

        if (teacher.getId() != null && teacherRepo.existsById(teacher.getId())) {
            throw new DuplicateKeyException("Already Added");
        }

        Teacher savedTeacher = teacherRepo.save(teacher);
        TeacherDto savedTeacherDto = modelMapper.map(savedTeacher, TeacherDto.class);
        return savedTeacherDto;
    }

    @Override
    public PaginatedResponseItemDto getAllTeachers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepo.findAll(pageable);

        List<TeacherDto> teacherDtos = teacherPage.getContent().stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDto.class))
                .collect(Collectors.toList());

        PaginatedResponseItemDto<TeacherDto> response = new PaginatedResponseItemDto<>();
        response.setList(teacherDtos);
        response.setTotalItems(teacherPage.getTotalElements());
        response.setCurrentPage(page);
        response.setTotalPages(teacherPage.getTotalPages());

        return response;
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        if (teacherDto.getId() == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }

        if (!teacherRepo.existsById(teacherDto.getId())) {
            throw new EntityNotFoundException("Student not found with ID: " + teacherDto.getId());
        }

        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        Teacher updatedTeacher = teacherRepo.save(teacher);
        TeacherDto updatedTeacherDto = modelMapper.map(updatedTeacher, TeacherDto.class);
        return updatedTeacherDto;
    }

    @Override
    public String deleteTeacherById(Long id) {
        if (teacherRepo.existsById(id)) {
        teacherRepo.deleteById(id);
        return "deleted";

        }
        else{
            throw new RuntimeException("No customer found that id");
        }
        }

    @Override
    public TeacherDto getTeacherById(Long id) {

        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("No teacher found with that ID");
        }

        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new RuntimeException("No teacher found with that ID"));
        return modelMapper.map(teacher, TeacherDto.class);
    }


}
