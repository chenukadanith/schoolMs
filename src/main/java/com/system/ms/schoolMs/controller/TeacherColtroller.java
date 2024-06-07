package com.system.ms.schoolMs.controller;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.service.TeacherService;
import com.system.ms.schoolMs.util.StandardRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@CrossOrigin

public class TeacherColtroller {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/saveTeacher")
    public ResponseEntity<StandardRespone<TeacherDto>> saveTeacher(@RequestBody TeacherDto teacherDto){
        TeacherDto saveTeacher=teacherService.saveTeacher(teacherDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",saveTeacher), HttpStatus.CREATED
        );
    }
    @GetMapping(value = "/getAllTeaches",params = {"page","size"})
    public ResponseEntity<StandardRespone<PaginatedResponseItemDto<TeacherDto>>>getAllTeaches(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size")int size
    ){
        PaginatedResponseItemDto teachers = teacherService.getAllTeachers(page,size);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",teachers), HttpStatus.OK
        );
    }
}
