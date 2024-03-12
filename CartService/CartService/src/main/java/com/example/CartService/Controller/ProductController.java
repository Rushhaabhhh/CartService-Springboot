package com.example.CartService.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.CartService.Models.product;
import com.example.CartService.Services.ProductService;

@RestController

public class ProductController {
    public static ProductService productService;

    public ProductController(ProductService productService) {
        ProductController.productService = productService;
    }

    @GetMapping("/test")
    public static String test() {
        return "test";
    }
    @GetMapping("/")
    public List<product> getproducts() {
        return productService.getproducts();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getCategories();
    }
    @GetMapping("/category/{categoryName}")
    public List<product> getproductsByCategory(@PathVariable String categoryName) {
        return productService.getproductsByCategory(categoryName);
    }

    @GetMapping("/{id}")
    public product getproduct(@PathVariable Long id) {
        return productService.getproduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        productService.deleteproduct(id);
        return "product deleted successfully";
    }
    @PostMapping("/add")
    public product addCart(@RequestBody product product) {
        return productService.addproduct(product);
    }

    @PutMapping("/update")
    public product updateCart(@RequestBody product product) {
        return productService.updateproduct(product);
    }
}
