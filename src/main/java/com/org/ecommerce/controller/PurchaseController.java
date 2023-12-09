package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.ecommerce.modal.Purchase;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.service.PurchaseService;
import com.org.ecommerce.service.UserServices;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService service;


    @GetMapping("/list")
    public ResponseEntity<List<Purchase>> get(){
        List<Purchase> res = service.getAllItems();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/create")
    public ResponseEntity<Purchase> create(Purchase body){
        Purchase res = service.createPurchase(body);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody Purchase body){
        Long res = service.updatePurchase(body);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Purchase body){
        service.deletePurchase(body.getID());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/listPurchaseByUserId")
    public ResponseEntity<List<Purchase>> getByUserId(long userId){
        List<Purchase> res = service.getAllItemsByUserId(userId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getPurchaseById")
    public ResponseEntity<Purchase> getPurchaseById(long id){
        Purchase res = service.getPurchaseById(id);
        return ResponseEntity.ok(res);
    }

}
