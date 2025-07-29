package com.example.ecommerce.controller;

import com.example.ecommerce.Entity.ProductEntity;
import com.example.ecommerce.request.ProductRequest;
import com.example.ecommerce.response.Response;
import com.example.ecommerce.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<Response<List<ProductEntity>>> searchProduct(@RequestParam String name){
       List<ProductEntity> product = productService.searchProduct(name);
        try {
           Response<List<ProductEntity>> response = new Response<>("Product fetched", true, product);
            return ResponseEntity.ok(response);
        }catch(RuntimeException e){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>("Product not found",false,null));
            }

        }

        @GetMapping("/get")
    public ResponseEntity<Response<List<ProductEntity>>> get(){
        List<ProductEntity> products = productService.getProduct();
        Response<List<ProductEntity>> response= new Response<>("product fetched",true,products);
        return ResponseEntity.ok(response);
        }

        @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id){
        try {
            productService.deletebyid(id);
            Response<String> response = new Response<>("product deleted Successfully", true, null);
            return ResponseEntity.ok(response);
        }catch(RuntimeException e){
            Response<String> response=new Response<>(e.getMessage(),false,null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }

        @DeleteMapping("/deleteall")
    public ResponseEntity<Response<String>> deleteall(){
        productService.deleteall();
        Response<String> response=new Response<>("product deleted",true,null);
        return ResponseEntity.ok(response);
        }

        }



