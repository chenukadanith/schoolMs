package com.system.ms.schoolMs.controller;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.TeacherDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.service.TeacherService;
import com.system.ms.schoolMs.util.StandardRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @GetMapping(value = "/getAllTeachers",params = {"page","size"})
    public ResponseEntity<StandardRespone<PaginatedResponseItemDto<TeacherDto>>>getAllTeaches(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size")int size
    ){
        PaginatedResponseItemDto teachers = teacherService.getAllTeachers(page,size);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",teachers), HttpStatus.OK
        );
    }

    @GetMapping(value = "/getTeacherById",params = {"id"})
    public ResponseEntity<StandardRespone<TeacherDto>>getTeacherById(@Param(value = "id")Long id){
        TeacherDto teacher=teacherService.getTeacherById(id);
        return new  ResponseEntity<>(
                new StandardRespone<>(201,"sucess",teacher), HttpStatus.OK);
    }

    @PutMapping(value = "/updateTeachers")
    public ResponseEntity<StandardRespone<TeacherDto>>updateTeacher(@RequestBody TeacherDto teacherDto){
        TeacherDto updateTeacher=teacherService.updateTeacher(teacherDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(200,"Sucess",updateTeacher), HttpStatus.OK
        );
    }


    @DeleteMapping(value = "/deleteTeacher",params = {"id"})
    public ResponseEntity<StandardRespone<String>>deleteTeacher(@RequestParam(value = "id")Long id){
        String deleted =teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(
                new StandardRespone<>(200,"sucess",deleted),HttpStatus.OK
        );
    }




}
