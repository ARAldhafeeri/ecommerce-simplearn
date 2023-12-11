package com.org.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.ecommerce.modal.PurchaseItem;
import com.org.ecommerce.modal.User;
import com.org.ecommerce.service.PurchaseItemService;
import com.org.ecommerce.service.UserServices;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/PurchaseItem")
public class PurchaseItemController {

    @Autowired
    private PurchaseItemService service;



    @PostMapping("/create")
    public ResponseEntity<PurchaseItem> create(PurchaseItem body){
        PurchaseItem res = service.createPurchaseItem(body);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody PurchaseItem body){
        int res = service.updateItem(body);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody PurchaseItem body){
        service.deleteItem(body.getID());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/getAllItemsByPurchaseId")
    public ResponseEntity<List<PurchaseItem>> getByUserId(long userId){
        List<PurchaseItem> res = service.getAllItemsByPurchaserId(userId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteAllItemsForPurchaseId")
    public ResponseEntity<String> getPurchaseItemById(long id){
        service.deleteAllItemsForPurchaseId(id);
        return ResponseEntity.ok("ok");
    }

}
