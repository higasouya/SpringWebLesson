package com.example.lesson.controller;

import com.example.lesson.entity.Product;
import com.example.lesson.entity.ProductAdd;
import com.example.lesson.service.PgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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

        @GetMapping("/product-add")
        public String productAdd(@ModelAttribute("ProductAdd") ProductAdd product){
            return "product-add";
        }

        @PostMapping("/product-add")
        public String insert(@Validated @ModelAttribute("ProductAdd") ProductAdd product, BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return "product-add";
            }
            Product conProduct = new Product(1,product.getName(),product.getPrice());
            pgProductService.insert(conProduct);
            return "redirect:http://localhost:8080/product-list";
        }

        @GetMapping("/product/update/{id}")
        public ModelAndView productEdit(@PathVariable("id") int id, @ModelAttribute("ProductAdd") ProductAdd product) {
            var table = pgProductService.findById(id);
            product.setId(table.id());
            product.setName(table.name());
            product.setPrice(table.price());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("update");
            modelAndView.addObject("id",id);

            return modelAndView;
        }

    @PostMapping("/product/update/{id}")
    public String update(@PathVariable("id") int id,@Validated @ModelAttribute("ProductAdd") ProductAdd product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "update";
        }
        Product conProduct = new Product(id,product.getName(),product.getPrice());
        pgProductService.update(conProduct);
        return "redirect:/product-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
            pgProductService.delete(id);
        return "redirect:/product-list";
    }

    @GetMapping("/product-management")
    public String management(){
            return "product-management";
    }

}
