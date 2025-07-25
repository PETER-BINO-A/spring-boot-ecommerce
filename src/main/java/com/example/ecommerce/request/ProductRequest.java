package com.example.ecommerce.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private int price;

    private String description;

    private String stock;


}
