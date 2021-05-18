package com.example.shop;

import com.example.shop.dtos.ProductRequest;
import com.example.shop.dtos.ProductResponse;
import com.example.shop.exception.RescueNotFoundException;
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

    public void addProduct(ProductRequest productRequest)
    {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNameOfProduct(productRequest.getNameOfProduct());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setAmount(productRequest.getAmount());
        productEntity.setPicture(productRequest.getPicture());
        productRepository.save(productEntity);
    }

    public ProductResponse findProductById(Integer id)
    {
        return StreamSupport.stream(toIterable(productRepository.findById(id)).spliterator(), false)
                .map(productEntity -> new ProductResponse(productEntity.getId(), productEntity.getNameOfProduct(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getAmount(), productEntity.getPicture()))
                .collect(Collectors.toList()).get(0);
    }

    public void updateProduct(Integer id, ProductRequest productRequest)
    {
        ProductEntity productEntityPom = productRepository.findById(id)
                .orElseThrow(() -> new RescueNotFoundException("Product with id: " + id + " doesn't exist."));
        productEntityPom.setNameOfProduct(productRequest.getNameOfProduct());
        productEntityPom.setDescription(productRequest.getDescription());
        productEntityPom.setPrice(productRequest.getPrice());
        productEntityPom.setAmount(productRequest.getAmount());
        productEntityPom.setPicture(productRequest.getPicture());
        productRepository.save(productEntityPom);
    }

    public void deleteProduct(Integer id)
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
}
