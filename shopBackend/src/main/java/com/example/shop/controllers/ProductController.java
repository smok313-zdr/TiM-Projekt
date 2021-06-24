package com.example.shop.controllers;

import com.example.shop.services.ProductService;
import com.example.shop.dtos.request.ProductRequest;
import com.example.shop.dtos.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.addProduct(productRequest),HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable String id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }


    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
        productService.updateProduct(id,productRequest);
        return new ResponseEntity<ProductResponse>(productService.updateProduct(id,productRequest),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/buy/{id}")
    public ResponseEntity buyProduct(@PathVariable String id, @RequestParam Integer amount) {
        productService.buyProduct(id,amount);
        return new ResponseEntity(HttpStatus.OK);
    }

}
