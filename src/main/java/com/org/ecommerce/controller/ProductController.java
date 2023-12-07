package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.ecommerce.modal.Product;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.service.ProductService;
import com.org.ecommerce.service.UserServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

    @PostMapping("/create")
    public ResponseEntity<Product> create(Product body){
        Product res = service.createProduct(body);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Product body){
        int res = service.updateProduct(body);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Product body){
        service.deleteProduct(body.getID());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(long id){
        Product res = service.getProductById(id);
        return ResponseEntity.ok(res);
    }

}
