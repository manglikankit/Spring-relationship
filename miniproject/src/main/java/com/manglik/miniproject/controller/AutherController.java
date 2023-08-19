package com.manglik.miniproject.controller;

import com.manglik.miniproject.request.AutherRequest;
import com.manglik.miniproject.response.AutherResponse;
import com.manglik.miniproject.service.AutherRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutherController {
    @Autowired
    private AutherRepoService autherRepoService;

    @PostMapping("/addAuther")
    public AutherResponse addAuther(@RequestBody AutherRequest autherReq){
        return autherRepoService.saveAuther(autherReq);
    }


    @GetMapping("getAllAuthers")
    public List<AutherResponse> getAllAuthers()
    {
        return autherRepoService.getAllAuther();
    }
    @GetMapping("getAuther/{id}")
    public Optional<AutherResponse> getAuther(@PathVariable int id)
    {
        return autherRepoService.getAuther(id);
    }

    @PutMapping("updateAuther/{id}")
    public void updateAuther(@RequestBody AutherRequest request){
        autherRepoService.updateAuther(request);
    }
    @DeleteMapping("deleteAuther/{id}")
    public void deleteAuther(@PathVariable int id){
        autherRepoService.deleteAuther(id);
    }
}
