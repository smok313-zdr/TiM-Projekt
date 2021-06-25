package com.example.shop.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String description;
    private Integer price;
    private Integer amount;
    private String picture;
    private String nameOfProduct;

}
