package com.manglik.miniproject.service;

import com.manglik.miniproject.entity.AutherEntity;
import com.manglik.miniproject.entity.CourseEntity;
import com.manglik.miniproject.repository.AutherEntityRepo;
import com.manglik.miniproject.repository.StudentEntityRepo;
import com.manglik.miniproject.entity.StudentEntity;
import com.manglik.miniproject.request.CourseRequest;
import com.manglik.miniproject.request.StudentRequest;
import com.manglik.miniproject.response.CourseResponse;
import com.manglik.miniproject.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentReopService {
    @Autowired
    private StudentEntityRepo studentRepo;
    @Autowired
    private AutherEntityRepo autherEntityRepo;
    public StudentResponse saveStudent(StudentRequest request){
        StudentEntity student = new StudentEntity();
        student.setStudentName(request.getStudentName());
            List<CourseEntity> courseEntityList = new ArrayList<>();
            for (CourseRequest courseRequest:request.getCourses()) {
                CourseEntity course = new CourseEntity();
                course.setCourseTitle(courseRequest.getCourseTitle());
                courseEntityList.add(course);
            }
        student.setCourseEntities(courseEntityList);

        StudentEntity courseEnt =  studentRepo.save(student);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setStudentName(student.getStudentName());
        List<CourseEntity> courseEntities = new ArrayList<>();
        for (CourseRequest courseRequest:request.getCourses()) {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setCourseId(courseRequest.getCourseId());
            courseEntity.setCourseTitle(courseRequest.getCourseTitle());
            courseEntities.add(courseEntity);
        }
        studentResponse.setCourses(courseEntities);
        return studentResponse;
    }
    public List<StudentResponse> getAllStudents()
    {
        List<StudentEntity> students = studentRepo.findAll();
        if (students.isEmpty()){
            throw new RuntimeException("Students not found");
        }
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for(StudentEntity studentEntity: students){
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setStudentName(studentEntity.getStudentName());
            studentResponse.setStudentId(studentEntity.getStudentId());
            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }
    public StudentResponse getStudent(int id)
    {
        Optional<StudentEntity>  student = studentRepo.findById(id);
        if (student.isEmpty()){
            throw new RuntimeException("Student not found by ID =>"+ id);
        }
        StudentEntity studentEntity = student.get();
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(studentEntity.getStudentId());
        studentResponse.setStudentName(studentEntity.getStudentName());
        studentResponse.setCourses(studentEntity.getCourseEntities());
        return studentResponse;
    }
    public void updateStudent(StudentRequest student){
        StudentEntity studentEntity = studentRepo.findById(student.getStudentId()).get();
        studentEntity.setStudentId(student.getStudentId());
        studentEntity.setStudentName(student.getStudentName());
        List<CourseEntity> courseEntityList = new ArrayList<>();
        for (CourseRequest courseRequest:student.getCourses()) {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setCourseTitle(courseRequest.getCourseTitle());
            courseEntity.setCourseId(courseRequest.getCourseId());
            courseEntityList.add(courseEntity);
        }
        studentEntity.setCourseEntities(courseEntityList);
        studentRepo.save(studentEntity);
    }
    public void deleteStudent( int id){
        StudentEntity studentEntity = studentRepo.findById(id).get();
        studentEntity.setCourseEntities(null);
        studentRepo.deleteById(id);
    }
}
