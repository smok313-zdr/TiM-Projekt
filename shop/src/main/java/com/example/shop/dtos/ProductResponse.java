package com.example.shop.dtos;

import lombok.Data;

@Data
public class ProductResponse {
    private Integer id;
    private String nameOfProduct;
    private String description;
    private Integer price;
    private Integer amount;
    private String picture;


    public ProductResponse(Integer id, String nameOfProduct, String description, Integer price, Integer amount, String picture) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.picture = picture;
    }
}
