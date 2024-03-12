package com.example.CartService.Models;

import java.util.Locale.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class product {
    public product(Long id2, String title2, String description2, double price2,
            com.example.CartService.Models.category category2, String image) {
        //TODO Auto-generated constructor stub
    }
    private long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
