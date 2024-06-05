package com.system.ms.schoolMs.controller;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.service.TeacherService;
import com.system.ms.schoolMs.util.StandardRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teacher")
@CrossOrigin

public class TeacherColtroller {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/saveTeacher")
    public ResponseEntity<StandardRespone<TeacherDto>> saveStudent(@RequestBody TeacherDto teacherDto){
        TeacherDto saveTeacher=teacherService.saveTeacher(teacherDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",saveTeacher), HttpStatus.CREATED
        );
    }
}
