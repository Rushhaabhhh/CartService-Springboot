package com.example.CartService.Services;

import java.util.List;

import com.example.CartService.DTOs.FakeStoreDTO;
import com.example.CartService.Models.product;

public interface ProductService {
    public List<product> getproducts();
    public product getproduct(Long id);
    public product addproduct(product product);
    public product updateproduct(product product);
    public void deleteproduct(Long id);
    public List<product> getproductsByCategory(String categoryName);
    public List<String> getCategories();
}
