package com.system.ms.schoolMs.service;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

    PaginatedResponseItemDto viewAllStudent(int page, int size);




    String deleteStudentById(Long studentId);

    StudentDto getStudentById(Long id);
}
