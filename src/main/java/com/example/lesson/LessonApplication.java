package com.example.lesson;

import com.example.lesson.entity.Product;
import com.example.lesson.service.PgProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LessonApplication {

	public static void main(String[] args) {

		var context = SpringApplication.run(LessonApplication.class, args);

		var productService = context.getBean(PgProductService.class);
		var test = new Product(3,"test",100);

		var products = productService.insert(test);
		System.out.println(products);
	}

}
