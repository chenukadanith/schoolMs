package com.system.ms.schoolMs.service;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;

import java.util.List;

public interface TeacherService {



    TeacherDto saveTeacher(TeacherDto teacherDto);
   PaginatedResponseItemDto getAllTeachers(int page, int size);
}
