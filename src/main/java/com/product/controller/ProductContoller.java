package com.product.controller;

import com.product.dto.ProductEvent;
import com.product.entity.Product;
import com.product.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductContoller {

    @Autowired
    ProductCommandService service;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent){
        return service.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductEvent productEvent) {
        return service.updateProduct(id, productEvent);
    }


}
