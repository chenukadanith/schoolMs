package com.system.ms.schoolMs.service.impl;

import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.entity.Teacher;
import com.system.ms.schoolMs.repo.TeacherRepo;
import com.system.ms.schoolMs.service.TeacherService;
import org.modelmapper.ModelMapper;
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
}
