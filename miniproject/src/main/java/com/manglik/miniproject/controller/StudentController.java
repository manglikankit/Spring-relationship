package com.manglik.miniproject.controller;

import com.manglik.miniproject.request.CourseRequest;
import com.manglik.miniproject.request.StudentRequest;
import com.manglik.miniproject.response.CourseResponse;
import com.manglik.miniproject.response.StudentResponse;
import com.manglik.miniproject.service.StudentReopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentReopService studentReopService;
    @PostMapping("/saveStudentWithCourse")
    public StudentResponse saveCourseWithStudents(@RequestBody StudentRequest request){
        return  studentReopService.saveStudent(request);
    }

    @GetMapping("getAllStudent")
    public List<StudentResponse> getAllStudent()
    {
        return studentReopService.getAllStudents();
    }

    @GetMapping("getStudent/{id}")
    public StudentResponse getStudent(@PathVariable int id)
    {
        return studentReopService.getStudent(id);
    }
    @PutMapping("updateStudent/{id}")
    public void updateStudentWithCourse(@RequestBody StudentRequest request){
        studentReopService.updateStudent(request);
    }
    @DeleteMapping("deleteStudent/{id}")
    public void deleteStudent(@PathVariable int id){
        studentReopService.deleteStudent(id);
    }
}
