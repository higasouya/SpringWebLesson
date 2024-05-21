package com.example.lesson.controller;

import com.example.lesson.entity.Product;
import com.example.lesson.service.PgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    @Autowired
    PgProductService pgProductService;


        @GetMapping("/product-list")
        public String productList(Model model) {
            model.addAttribute("products",pgProductService.findAll());
           return "product-list";
        }

        @GetMapping("/product/{id}")
        public String productDetail(@PathVariable("id") int id, Model model) {
            model.addAttribute("product",pgProductService.findById(id));
            return "product-detail";
        }
}
