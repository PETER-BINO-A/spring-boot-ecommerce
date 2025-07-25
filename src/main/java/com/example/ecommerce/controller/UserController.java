package com.example.ecommerce.controller;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.request.UserRequest;
import com.example.ecommerce.response.Response;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Response<UserEntity>> add(@RequestBody UserRequest userRequest){
        UserEntity createuser= userService.add(userRequest);
        Response<UserEntity> response=new Response<>("user created",true,createuser);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<UserEntity>> edit(@RequestBody UserRequest userRequest, @PathVariable Long id){
       UserEntity editeduser= userService.edit(userRequest,id);
        Response<UserEntity> response = new Response<>("User updated", true, editeduser);
        return ResponseEntity.ok(response);


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<UserEntity>> getbyid(@PathVariable Long id) {
        UserEntity user = userService.getbyid(id);
        try {
            return ResponseEntity.ok(new Response<>("User found", true, user));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("User not found", false, null));
        }
    }



    @GetMapping("/get")
    public ResponseEntity<Response<List<UserEntity>>> getall(){
        List<UserEntity> user = userService.getall();
        Response<List<UserEntity>> response=new Response<>("user fetched",true,user);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<String>> deletebyid(@PathVariable Long id){
      String result= (userService.deletebyid(id));
        try{
          Response<String> response =new Response<>("user deleted",true,result);
          return ResponseEntity.ok(response);
      }
      catch(RuntimeException e){
          Response<String> response =new Response<>("user Deleted",false,null );
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response<String>> deleteall(){
        String user = userService.deleteall();
        Response<String> response=new Response<>("user Entity",true,user);
         return ResponseEntity.ok(response);
    }
}
