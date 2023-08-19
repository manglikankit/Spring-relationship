package com.manglik.miniproject.controller;

import com.manglik.miniproject.entity.AutherEntity;
import com.manglik.miniproject.entity.StudentEntity;
import com.manglik.miniproject.request.StudentRequest;
import com.manglik.miniproject.response.CourseResponse;
import com.manglik.miniproject.response.StudentResponse;
import com.manglik.miniproject.service.CourseRepoService;
import com.manglik.miniproject.entity.CourseEntity;
import com.manglik.miniproject.request.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseRepoService coursesService;

    @PostMapping("/addCourse")
    public CourseResponse addCourse(@RequestBody CourseRequest courseRequest){
        return  coursesService.saveCourse(courseRequest);
    }

    @PostMapping("/saveCourseWithStudents")
    public CourseResponse saveCourseWithStudents(@RequestBody CourseRequest courseRequest){
        return  coursesService.saveCourseWithStudent(courseRequest);
    }

    @GetMapping("getAllCourses")
    public List<CourseResponse> getAllCourses()
    {
       return coursesService.getAllCourses();
    }

    @GetMapping("getCourse/{id}")
    public CourseResponse getCourse(@PathVariable int id)
    {
          return coursesService.getCourse(id);
    }
    @PutMapping("updateCourse/{id}")
    public void updateCourseWithStudent(@RequestBody CourseRequest courseRequest){
        coursesService.updateCourseWithStudent(courseRequest);
    }
    @DeleteMapping("deleteCourse/{id}")
    public void deleteCourse(@PathVariable int id){
        coursesService.deleteCourse(id);
    }
}
