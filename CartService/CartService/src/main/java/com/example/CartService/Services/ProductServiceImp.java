package com.example.CartService.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CartService.DTOs.FakeStoreDTO;
import com.example.CartService.Models.category;
import com.example.CartService.Models.product;

@Service
public class ProductServiceImp implements ProductService{

    private final String url = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<product> getproducts() {

        List<FakeStoreDTO> FakeStoreDTOS = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreDTO>>() {}).getBody();

        assert FakeStoreDTOS != null;

        return FakeStoreDTOS.stream().map(this::mapToproduct).toList();
    }

    private product mapToproduct(FakeStoreDTO FakeStoreDTO) {
        category category = new category(1L, FakeStoreDTO.getCategory());
        category.setName(FakeStoreDTO.getCategory());
        return new product(FakeStoreDTO.getId(), FakeStoreDTO.getTitle(), FakeStoreDTO.getDescription(), FakeStoreDTO.getPrice(), category , FakeStoreDTO.getImage());

}

    @Override
    public product getproduct(Long id) {

        FakeStoreDTO FakeStoreDTO = restTemplate.getForObject(url + "/" + id, FakeStoreDTO.class);

        assert FakeStoreDTO != null;

        return mapToproduct(FakeStoreDTO);
    }

    @Override
    public product addproduct(product product) {

//        Add product to the fakestoreapi
        FakeStoreDTO FakeStoreDTO = new FakeStoreDTO();
        FakeStoreDTO.setTitle(product.getTitle());
        FakeStoreDTO.setDescription(product.getDescription());
        FakeStoreDTO.setCategory(product.getCategory().name());
        FakeStoreDTO.setPrice(product.getPrice());
        FakeStoreDTO.setImage(product.getImageUrl());

        FakeStoreDTO response = restTemplate.postForObject(url, FakeStoreDTO, FakeStoreDTO.class);

        assert response != null;

        return mapToproduct(response);
    }

    @Override
    public product updateproduct(product product) {
//        Update product to the fakestoreapi
        FakeStoreDTO FakeStoreDTO = new FakeStoreDTO();
        FakeStoreDTO.setId(product.getId());
        FakeStoreDTO.setTitle(product.getTitle());
        FakeStoreDTO.setDescription(product.getDescription());
        FakeStoreDTO.setCategory(product.getCategory().name());
        FakeStoreDTO.setPrice(product.getPrice());
        FakeStoreDTO.setImage(product.getImageUrl());

        restTemplate.put(url + "/" + product.getId(), FakeStoreDTO);

        return product;
    }

    @Override
    public void deleteproduct(Long id) {
        restTemplate.delete(url + "/" + id);
    }

        @Override
        public List<product> getproductsByCategory(String categoryName) {
    //     https://fakestoreapi.com/products/category/jewelery
            List<FakeStoreDTO> FakeStoreDTOS = restTemplate.exchange(
                    url + "/category/" + categoryName,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<FakeStoreDTO>>() {}).getBody();

            assert FakeStoreDTOS != null;

            return FakeStoreDTOS.stream().map(this::mapToproduct).toList();
        }

    @Override
    public List<String> getCategories() {

        List<String> categories = restTemplate.exchange(
                url + "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}).getBody();

        assert categories != null;

        return categories;
    }
}
