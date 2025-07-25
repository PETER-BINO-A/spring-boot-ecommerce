package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int price;

    private String stock;


}
