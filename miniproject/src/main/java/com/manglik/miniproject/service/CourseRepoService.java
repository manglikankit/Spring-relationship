package com.manglik.miniproject.service;

import com.manglik.miniproject.entity.StudentEntity;
import com.manglik.miniproject.repository.AutherEntityRepo;
import com.manglik.miniproject.repository.CourseEntityRepo;
import com.manglik.miniproject.entity.AutherEntity;
import com.manglik.miniproject.entity.CourseEntity;
import com.manglik.miniproject.repository.StudentEntityRepo;
import com.manglik.miniproject.request.CourseRequest;
import com.manglik.miniproject.request.StudentRequest;
import com.manglik.miniproject.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseRepoService {
    @Autowired
    private CourseEntityRepo coursesRepo;

    @Autowired
    private StudentEntityRepo studentEntityRepo;

    @Autowired
    private AutherEntityRepo autherEntityRepo;

    public CourseResponse saveCourse(CourseRequest courseRequest){
        Optional<AutherEntity> authorEntity = autherEntityRepo.findById(courseRequest.getAuthorId());
        if(authorEntity.isPresent()) {
            CourseEntity course = new CourseEntity();
            course.setCourseTitle(courseRequest.getCourseTitle());
            course.setAutherEntity(authorEntity.get());
            CourseEntity courseEntity = coursesRepo.save(course);

            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseId(courseEntity.getCourseId());
            courseResponse.setCourseTitle(courseEntity.getCourseTitle());
            courseResponse.setAuthorId(courseEntity.getAutherEntity().getAutherId());
            return courseResponse;
        }
        return null;
    }

    public CourseResponse saveCourseWithStudent(CourseRequest courseReq){
        Optional<AutherEntity> authorEntity = autherEntityRepo.findById(courseReq.getAuthorId());
        CourseEntity courseEntity = new CourseEntity();
        if(authorEntity.isPresent()) {
            courseEntity.setCourseTitle(courseReq.getCourseTitle());
            courseEntity.setAutherEntity(authorEntity.get());
            List<StudentEntity> studentEntityList = new ArrayList<>();

            for (StudentRequest studentRequest:courseReq.getStudents()) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudentName(studentRequest.getStudentName());
                studentEntityList.add(studentEntity);
            }
            courseEntity.setStudentEntities(studentEntityList);
        }
        CourseEntity courseEnt =  coursesRepo.save(courseEntity);
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCourseId(courseEnt.getCourseId());
        courseResponse.setCourseTitle(courseEnt.getCourseTitle());
        courseResponse.setAuthorId(courseEnt.getAutherEntity().getAutherId());
        List<StudentEntity> studentEntityList = new ArrayList<>();
        for (StudentRequest studentRequest:courseReq.getStudents()) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentName(studentRequest.getStudentName());
            studentEntityList.add(studentEntity);
        }
        courseResponse.setStudents(studentEntityList);
        return courseResponse;
    }

    public List<CourseResponse> getAllCourses()
    {
        List<CourseEntity> courseEntities = coursesRepo.findAll();
        if (courseEntities.isEmpty()){
            throw new RuntimeException("No course is available");
        }
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for(CourseEntity courseEntity: courseEntities){
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setAuthorId(courseEntity.getAutherEntity().getAutherId());
            courseResponse.setCourseTitle(courseEntity.getCourseTitle());
            courseResponse.setCourseId(courseEntity.getCourseId());
//            courseResponse.setStudents(courseEntity.getStudentEntities());
            courseResponseList.add(courseResponse);
        }
        return courseResponseList;
    }

    public CourseResponse getCourse(int id)
    {
        Optional<CourseEntity> courseEnt = coursesRepo.findById(id);
        if (courseEnt.isEmpty()){
            throw new RuntimeException("Course not found by ID =>"+ id);
        }
        CourseEntity courseEntity = courseEnt.get();
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setAuthorId(courseEntity.getAutherEntity().getAutherId());
        courseResponse.setCourseTitle(courseEntity.getCourseTitle());
        courseResponse.setCourseId(courseEntity.getCourseId());
        courseResponse.setStudents(courseEntity.getStudentEntities());
        return courseResponse;
    }

    public void updateCourseWithStudent(CourseRequest courseReq){
        CourseEntity courseEntity = coursesRepo.findById(courseReq.getCourseId()).get();
            courseEntity.setCourseId(courseReq.getCourseId());
            courseEntity.setCourseTitle(courseReq.getCourseTitle());
            courseEntity.setAutherEntity(autherEntityRepo.findById(courseReq.getAuthorId()).get());
            List<StudentEntity> studentEntityList = new ArrayList<>();
            for (StudentRequest studentRequest:courseReq.getStudents()) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudentId(studentRequest.getStudentId());
                studentEntity.setStudentName(studentRequest.getStudentName());
                studentEntityList.add(studentEntity);
            }
            courseEntity.setStudentEntities(studentEntityList);
            coursesRepo.save(courseEntity);
    }

    public void deleteCourse(int id){
        CourseEntity courseEntity = coursesRepo.findById(id).get();
            courseEntity.setAutherEntity(null);
            courseEntity.setStudentEntities(null);
            coursesRepo.deleteById(id);
    }
}
