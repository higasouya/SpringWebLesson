package com.example.lesson.service;

import com.example.lesson.dao.ProductDao;
import com.example.lesson.entity.Product;
import com.example.lesson.entity.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PgProductService implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(){
        return productDao.findAll();
    }

    public Product findById(int num){
        var result = productDao.findById(num);
        if(result != null){
            return result;
        }else{
            throw new ProductNotFoundException();
        }
    }

    public int insert(Product product){
        try{
            var result = productDao.insert(product);
            return result;
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Product product){
        return productDao.update(product);
    }

    public int delete(int num){
        return productDao.delete(num);
    }

}
