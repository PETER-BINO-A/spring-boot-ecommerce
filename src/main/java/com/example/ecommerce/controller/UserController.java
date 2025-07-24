package com.example.ecommerce.controller;

import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.request.UserRequest;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public UserEntity add(@RequestBody UserRequest userRequest){
        return userService.add(userRequest);
    }

    @PutMapping("/edit/{id}")
    public UserEntity edit(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return userService.edit(userRequest,id);
    }

    @GetMapping("/get/{id}")
    public UserEntity getbyid(@PathVariable Long id){
        return userService.getbyid(id);
    }

    @GetMapping("/get")
    public List<UserEntity> getall(){
        return userService.getall();
    }

    @DeleteMapping("/delete/{id}")
    public String deletebyid(@PathVariable Long id){
        return userService.deletebyid(id);
    }

    @DeleteMapping("/delete")
    public String deleteall(){
        return userService.deleteall();
    }
}
