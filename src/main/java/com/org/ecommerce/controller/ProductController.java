package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.org.ecommerce.modal.Product;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.service.ProductService;
import com.org.ecommerce.service.UserServices;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/list")
    public ResponseEntity<List<Product>> get(){
        List<Product> res = service.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getProductById/:id")
    public ResponseEntity<Product> getProductById(@RequestParam long id){
        Product res = service.getProductById(id);
        return ResponseEntity.ok(res);
    }

}
