package com.example.ecommerce.service;

import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserEntity add(UserRequest userRequest){
        UserEntity user=new UserEntity();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setMobile(userRequest.getMobile());
        user.setPassword( userRequest.getPassword());
        return userRepository.save(user);

    }

    public UserEntity edit(UserRequest userRequest, Long id){
        UserEntity ExistingUser= userRepository.findById(id).orElseThrow(() ->new RuntimeException("user not found"));
        ExistingUser.setName(userRequest.getName());
        ExistingUser.setEmail(userRequest.getEmail());
        ExistingUser.setRole(userRequest.getRole());
        ExistingUser.setMobile(userRequest.getMobile());
        ExistingUser.setPassword(userRequest.getPassword());
        return userRepository.save(ExistingUser);
    }

    public UserEntity getbyid(Long id){
        return userRepository.findById(id).get();
    }

    public List<UserEntity> getall(){
        return userRepository.findAll();
    }

    public  String deletebyid(Long id){
        userRepository.deleteById(id);
        return "Deleted ";

    }

    public String deleteall(){
        userRepository.deleteAll();
        return "Deleted ";
    }

}
