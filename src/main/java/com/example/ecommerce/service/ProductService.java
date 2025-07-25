package com.example.ecommerce.service;

import com.example.ecommerce.Entity.ProductEntity;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public ProductEntity addproduct(ProductRequest productRequest){
        ProductEntity product = new ProductEntity();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        return productRepository.save(product);
    }

    public ProductEntity updateproduct(ProductRequest productRequest,Long id){
        ProductEntity ExistingProduct = productRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
        ExistingProduct.setName(productRequest.getName());
        ExistingProduct.setDescription(productRequest.getDescription());
        ExistingProduct.setPrice(productRequest.getPrice());
        ExistingProduct.setStock(productRequest.getStock());
        return productRepository.save(ExistingProduct);
    }
}
