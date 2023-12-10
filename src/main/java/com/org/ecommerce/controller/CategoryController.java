package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.ecommerce.modal.Category;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.service.CategoryService;
import com.org.ecommerce.service.UserServices;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @GetMapping("/list")
    public ResponseEntity<List<Category>> get(){
        List<Category> res = service.getAllCategories();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<Category> getCategoryById(long id){
        Category res = service.getCategoryById(id);
        return ResponseEntity.ok(res);
    }

}
