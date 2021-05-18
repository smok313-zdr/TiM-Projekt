package com.example.shop;

import com.example.shop.dtos.ProductRequest;
import com.example.shop.dtos.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    // add new product - CREATE
    @PostMapping("/shop")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    // show all products - READ
    @GetMapping("/shop")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    // show product by id
    @GetMapping("/shop/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    // edit product - UPDATE
    @PutMapping("/shop/{id}")
    public ResponseEntity editProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest){
        productService.updateProduct(id,productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    // delete product - DELETE
    @DeleteMapping("/shop/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
