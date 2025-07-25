package com.example.ecommerce.controller;

import com.example.ecommerce.Entity.ProductEntity;
import com.example.ecommerce.request.ProductRequest;
import com.example.ecommerce.response.Response;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Response<ProductEntity>> add(@RequestBody ProductRequest productRequest){
        ProductEntity result = productService.addproduct(productRequest);
      Response<ProductEntity> response =new Response<>("Product added",true,result);
      return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<ProductEntity>> update(@RequestBody ProductRequest productRequest,@PathVariable Long id){
        ProductEntity result = productService.updateproduct(productRequest,id);
        try{
            return ResponseEntity.ok(new Response<>("Product updated",true,result));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>("Product not found",false,null));
        }
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<ProductEntity> get(@Path){
//        ResponseEntity<ProductEntity> result = productService.getbyid(id);
//        try {
//            ResponseEntity<ProductEntity> response = new ResponseEntity<>("Product fetched", true, result);
//            return ResponseEntity.ok(response);
//        }catch(RuntimeException e){
//              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>("id not found"))
//            }
//
//        }

}
