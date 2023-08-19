package com.manglik.miniproject.service;

import com.manglik.miniproject.entity.StudentEntity;
import com.manglik.miniproject.repository.AutherEntityRepo;

import com.manglik.miniproject.entity.AutherEntity;
import com.manglik.miniproject.entity.CourseEntity;
import com.manglik.miniproject.request.AutherRequest;
import com.manglik.miniproject.request.StudentRequest;
import com.manglik.miniproject.response.AutherResponse;
import com.manglik.miniproject.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutherRepoService {
    @Autowired
    private AutherEntityRepo autherEntityRepo;


    public AutherResponse saveAuther(AutherRequest authRequest){
            AutherEntity auther = new AutherEntity();
            auther.setFirstName(authRequest.getFirstName());
            auther.setLastName(authRequest.getLastName());
            AutherEntity autherEntity = autherEntityRepo.save(auther);

            AutherResponse autherResponse = new AutherResponse();
        autherResponse.setFirstName(auther.getFirstName());
        autherResponse.setLastName(auther.getLastName());
        autherResponse.setAutherId(auther.getAutherId());
            return autherResponse;
    }

    public List<AutherResponse> getAllAuther()
    {
        List<AutherEntity> authers = autherEntityRepo.findAll();
        if (authers.isEmpty()){
            throw new RuntimeException("Auther not found");
        }

        List<AutherResponse> autherResponseList = new ArrayList<>();
        for(AutherEntity autherEntity: authers){
            AutherResponse autherResponse = new AutherResponse();
            autherResponse.setAutherId(autherEntity.getAutherId());
            autherResponse.setFirstName(autherEntity.getFirstName());
            autherResponse.setLastName(autherEntity.getLastName());
            autherResponseList.add(autherResponse);
        }
        return autherResponseList;
    }
    public Optional<AutherResponse> getAuther(int id)
    {
        Optional<AutherEntity> auther = autherEntityRepo.findById(id);
        if (auther.isEmpty()){
            throw new RuntimeException("Auther not found by ID =>"+ id);
        }
        AutherEntity autherEntity = auther.get();
        AutherResponse autherResponse = new AutherResponse();
        autherResponse.setFirstName(autherEntity.getFirstName());
        autherResponse.setLastName(autherEntity.getLastName());
        autherResponse.setAutherId(autherEntity.getAutherId());
        return Optional.of(autherResponse);
    }
    public void updateAuther(AutherRequest request){
        AutherEntity autherEntity = autherEntityRepo.findById(request.getAutherId()).get();
        autherEntity.setAutherId(request.getAutherId());
        autherEntity.setFirstName(request.getFirstName());
        autherEntity.setLastName(request.getLastName());
        autherEntityRepo.save(autherEntity);

    }
    public void deleteAuther( int id){
        Optional<AutherEntity> auther = autherEntityRepo.findById(id);
        List<CourseEntity> courses = auther.get().getCourseEntities();
        for (CourseEntity course : courses){
            course.setAutherEntity(null);
        }
        autherEntityRepo.deleteById(id);
    }

}
