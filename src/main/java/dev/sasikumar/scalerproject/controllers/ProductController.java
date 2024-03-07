package dev.sasikumar.scalerproject.controllers;

import dev.sasikumar.scalerproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.scalerproject.DTOs.ErrorDTO;
import dev.sasikumar.scalerproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.scalerproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.scalerproject.exceptions.NotValidCategoryException;
import dev.sasikumar.scalerproject.exceptions.ProductNotFoundException;
import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;
import dev.sasikumar.scalerproject.services.FakeStoreProductService;
import dev.sasikumar.scalerproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


// Acts like a waiter
// Takes all responses and sends them to the service


@RestController
public class ProductController {
    // Constructor, Dependency Injection
    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController (ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }



    // 1. create a product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO request) {
        return productService.createProduct(request);
    }

    // 2. get a single product
    @GetMapping("/products/{Id}")
    public Product getSingleProductDetails(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    // 3. update a product
    @PutMapping("/products/{Id}")
    public Product updateProduct(@PathVariable("Id") Long productId,
                                 @RequestBody UpdateProductRequestDTO updateRequest) {
        return productService.updateProduct(productId, updateRequest);
    }

    // 4. delete a product
    @DeleteMapping("/products/{Id}")
    public Product deleteProduct(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.deleteProduct(productId);
    }

    // 5. get all categories
    @GetMapping("/products/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }

    // 6. get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 7. get all products category wise
    @GetMapping("/products/category/{category}")
    public List<Product> getAllProductsCategoryWise(@PathVariable("category") String category) throws NotValidCategoryException{
        return productService.getAllProductsCategoryWise(category);
    }


}
