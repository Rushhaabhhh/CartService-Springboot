package com.example.CartService.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class category {
    
    private Long id;
    private String name;

    public category() {
    }

    public category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
