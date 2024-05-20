package com.example.lesson.service;

import com.example.lesson.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int num);

    int insert(Product product);

    int update(Product product);

    int delete(int num);
}
