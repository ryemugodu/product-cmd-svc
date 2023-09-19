package com.product.service;

import com.product.dto.ProductEvent;
import com.product.entity.Product;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product product0 =  repository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct", product0);
        kafkaTemplate.send("product-event-topic", event);
        return product0;
    }

    public Product updateProduct(Long id, ProductEvent productEveProduct){
        Product product = productEveProduct.getProduct();
        Product existingProduct = repository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        ProductEvent event = new ProductEvent("UpdateProduct", existingProduct);
        Product product0 = repository.save(existingProduct);
        kafkaTemplate.send("product-event-topic", event);
        return product0;
    }

}
