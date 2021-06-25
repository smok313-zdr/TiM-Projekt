package com.example.shop.services;

import com.example.shop.dtos.request.ProductRequest;
import com.example.shop.dtos.response.ProductResponse;
import com.example.shop.entities.ProductEntity;
import com.example.shop.exception.RescueNotFoundException;
import com.example.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAll()
    {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productEntity -> new ProductResponse(productEntity.getId(), productEntity.getNameOfProduct(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAmount(), productEntity.getPicture()))
                .collect(Collectors.toList());
    }

    public ProductResponse addProduct(ProductRequest productRequest)
    {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNameOfProduct(productRequest.getNameOfProduct());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setAmount(productRequest.getAmount());
        productEntity.setPicture(productRequest.getPicture());
        productRepository.save(productEntity);
        return new ProductResponse(productEntity.getId(), productEntity.getNameOfProduct(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAmount(), productEntity.getPicture());

    }

    public ProductResponse findProductById(String id)
    {
        return StreamSupport.stream(toIterable(productRepository.findById(id)).spliterator(), false)
                .map(productEntity -> new ProductResponse(productEntity.getId(), productEntity.getNameOfProduct(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAmount(), productEntity.getPicture()))
                .collect(Collectors.toList()).get(0);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest)
    {
        ProductEntity productEntityPom = productRepository.findById(id)
                .orElseThrow(() -> new RescueNotFoundException("Product with id: " + id + " doesn't exist."));
        productEntityPom.setNameOfProduct(productRequest.getNameOfProduct());
        productEntityPom.setDescription(productRequest.getDescription());
        productEntityPom.setPrice(productRequest.getPrice());
        productEntityPom.setAmount(productRequest.getAmount());
        productEntityPom.setPicture(productRequest.getPicture());
        productRepository.save(productEntityPom);
        return new ProductResponse(productEntityPom.getId(), productEntityPom.getNameOfProduct(), productEntityPom.getDescription(), productEntityPom.getPrice(), productEntityPom.getAmount(), productEntityPom.getPicture());
    }

    public void deleteProduct(String id)
    {
        ProductEntity productEntityPom = productRepository.findById(id)
                .orElseThrow(() -> new RescueNotFoundException("Product with id: " + id + " doesn't exist."));
        if(productEntityPom.getAmount()>0)
        {
            productEntityPom.setAmount(productEntityPom.getAmount()-1);
            productRepository.save(productEntityPom);
            System.out.println("Customer just bought 1 " + productEntityPom.getNameOfProduct() + ", " + productEntityPom.getAmount() + " currently in stock.");
            if(productEntityPom.getAmount() ==0)
                productRepository.delete(productEntityPom);
        }
        else if(productEntityPom.getAmount() == 0 || productEntityPom.getAmount()<0)
            productRepository.delete(productEntityPom);
    }

    public static <T> Iterable<T> toIterable(Optional<T> o) { // pomocnicza konwersja
        return o.map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    public void buyProduct(String id, Integer amount) {
        ProductEntity productEntityPom = productRepository.findById(id)
                .orElseThrow(() -> new RescueNotFoundException("Product with id: " + id + " doesn't exist."));
        if(productEntityPom.getAmount()-amount>=0)
        {
            productEntityPom.setAmount(productEntityPom.getAmount()-amount);
            productRepository.save(productEntityPom);
            System.out.println("Customer just bought "+amount+" " + productEntityPom.getNameOfProduct() + ", " + productEntityPom.getAmount() + " currently in stock.");
            if(productEntityPom.getAmount() == 0)
                productRepository.delete(productEntityPom);
        }
        else productRepository.delete(productEntityPom);
    }
}
