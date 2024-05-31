package com.system.ms.schoolMs.service.impl;

import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.entity.Teacher;
import com.system.ms.schoolMs.repo.TeacherRepo;
import com.system.ms.schoolMs.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
}
