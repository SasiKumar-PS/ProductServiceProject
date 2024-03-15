package dev.sasikumar.productserviceproject.controllers;

import dev.sasikumar.productserviceproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.productserviceproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.productserviceproject.exceptions.NotValidCategoryException;
import dev.sasikumar.productserviceproject.exceptions.ProductNotFoundException;
import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;
import dev.sasikumar.productserviceproject.services.ProductService;
import dev.sasikumar.productserviceproject.services.CheckingProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


// Acts like a waiter
// Takes all responses and sends them to the service
@RestController
public class ProductController {
    // Constructor, Dependency Injection
    private final ProductService productService;

    public ProductController (@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }



    // 1. create a product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDTO request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // 2. get a single product
    @GetMapping("/products/{Id}")
    public ResponseEntity<Product> getSingleProductDetails(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    // 3. update a product
    @PutMapping("/products/{Id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("Id") Long productId,
                                 @RequestBody UpdateProductRequestDTO updateRequest) throws ProductNotFoundException  {
        Product product = productService.updateProduct(productId, updateRequest);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    // 4. delete a product
    @DeleteMapping("/products/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        String message = productService.deleteProduct(productId);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    // 5. get all categories
    @GetMapping("/products/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories = productService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
    }

    // 6. get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }

    // 7. get all products category wise
    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsCategoryWise(@PathVariable("category") String category) throws NotValidCategoryException{
        List<Product> allProducts = productService.getAllProductsCategoryWise(category);
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }
}
