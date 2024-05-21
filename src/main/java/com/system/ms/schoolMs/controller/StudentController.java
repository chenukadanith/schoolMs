package com.system.ms.schoolMs.controller;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.entity.Student;
import com.system.ms.schoolMs.service.StudentService;
import com.system.ms.schoolMs.util.StandardRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/save")
    public ResponseEntity<StandardRespone<StudentDto>> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto saveStudent=studentService.saveStudent(studentDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",saveStudent), HttpStatus.CREATED
                );
    }


}
