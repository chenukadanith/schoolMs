package com.system.ms.schoolMs.controller;

import com.system.ms.schoolMs.dto.StudentDto;
import com.system.ms.schoolMs.dto.paginated.PaginatedResponseItemDto;
import com.system.ms.schoolMs.entity.Student;
import com.system.ms.schoolMs.exception.StudentNotFoundException;
import com.system.ms.schoolMs.service.StudentService;
import com.system.ms.schoolMs.util.StandardRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/saveStudent")
    public ResponseEntity<StandardRespone<StudentDto>> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto saveStudent=studentService.saveStudent(studentDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(201,"Sucess",saveStudent), HttpStatus.CREATED
                );
    }
    //
    @PostMapping("/auth")
    public ResponseEntity<StandardRespone<StudentDto>> login(@RequestBody StudentDto studentDto){
        try{
            StudentDto student = studentService.checkStudent(studentDto.getUsername(), studentDto.getPassword());
            return new ResponseEntity<>(new StandardRespone<>(200,"Success",student),HttpStatus.CREATED);
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(new StandardRespone<>(401,e.getMessage(),studentDto),HttpStatus.CREATED);
        }
    }
    //
   @PutMapping("/updateStudent")
    public ResponseEntity<StandardRespone<StudentDto>>updateStudent(@RequestBody StudentDto studentDto){
        StudentDto updateStudent=studentService.updateStudent(studentDto);
        return  new ResponseEntity<>(
                new StandardRespone<>(200 ,"sucess",updateStudent),HttpStatus.OK
        );
   }
   @GetMapping (value = "/veiwAllStudents",params = {"page","size"})

    public ResponseEntity<StandardRespone<PaginatedResponseItemDto<StudentDto>>> veiwAllStudent(@RequestParam (value = "page") int page,
                                                                           @RequestParam(value = "size")int size){
      PaginatedResponseItemDto veiwAllStudent=studentService.viewAllStudent(page,size);
       return  new ResponseEntity<>(
               new StandardRespone<>(200 ,"sucess",veiwAllStudent),HttpStatus.OK
       );
   }

   @GetMapping(value = "/getStudentById",params = {"id"})
   public ResponseEntity<StandardRespone<StudentDto>>getStudentById(@RequestParam(value = "id")Long id){
        StudentDto student=studentService.getStudentById(id);
        return new ResponseEntity<>(new StandardRespone<>(200,"sucess",student),HttpStatus.OK);
   }

   @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<StandardRespone<String>> deleteStudent(@PathVariable(value = "id") long studentId){
        String deletded =studentService.deleteStudentById(studentId);
        return  new ResponseEntity<>
                (new StandardRespone<>(200,"sucess",deletded),HttpStatus.OK);

   }


}
