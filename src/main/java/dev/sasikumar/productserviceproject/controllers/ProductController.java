package dev.sasikumar.productserviceproject.controllers;

import dev.sasikumar.productserviceproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.productserviceproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.productserviceproject.exceptions.NotValidCategoryException;
import dev.sasikumar.productserviceproject.exceptions.ProductNotFoundException;
import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;
import dev.sasikumar.productserviceproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/products")
@EnableCaching
public class ProductController {
    // Constructor, Dependency Injection
    private final ProductService productService;

    public ProductController (@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }



    // 1. create a product
    @CachePut(value = "product", key = "#product.id", unless = "#product == null || #product.title == null")
    @PostMapping("/")
    public Product createProduct(@RequestBody CreateProductRequestDTO request) {
        return productService.createProduct(request.getTitle(), request.getPrice(), request.getCategory(), request.getDescription(), request.getImage());
        // return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // 2. get a single product
    @Cacheable(value = "product")
    @GetMapping("/{Id}")
    public Product getSingleProductDetails(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
        // return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    // 3. update a product
    @CachePut(value = "product", key = "#productId", unless = "#product == null || #product.title == null")
    @PutMapping("/{Id}")
    public Product updateProduct(@PathVariable("Id") Long productId,
                                 @RequestBody UpdateProductRequestDTO updateRequest) throws ProductNotFoundException  {

        return productService.updateProduct (productId, updateRequest.getTitle(), updateRequest.getPrice(), updateRequest.getCategory(), updateRequest.getDescription(), updateRequest.getTitle());
        // return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    // 4. delete a product
    @CacheEvict(value = "product", key = "#productId")
    @DeleteMapping("/{Id}")
    public Long deleteProduct(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.deleteProduct(productId);
        // return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    // 5. get all categories
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories = productService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
    }

    // 6. get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }

    // 7. get all products category wise
    @GetMapping("category/{category}")
    public ResponseEntity<List<Product>> getAllProductsCategoryWise(@PathVariable("category") String category) throws NotValidCategoryException{
        List<Product> allProducts = productService.getAllProductsCategoryWise(category);
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }
}
