package dev.sasikumar.scalerproject.controllers;

import dev.sasikumar.scalerproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.scalerproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;
import dev.sasikumar.scalerproject.services.ProductService;
import org.springframework.web.bind.annotation.*;
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
        return productService.createProduct(
                request.getTitle(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription(),
                request.getImage()
        );
    }

    // 2. get a single product
    @GetMapping("/products/{Id}")
    public Product getSingleProductDetails(@PathVariable("Id") Long productId) {
        return productService.getSingleProduct(productId);
    }

    // 3. update a product
    @PutMapping("/product/{Id}")
    public Product updateProduct(@PathVariable("Id") Long productId,
                                 @RequestBody UpdateProductRequestDTO updateRequest) {

        Product request = new Product();
        request.setId(productId);
        request.setTitle(updateRequest.getTitle());
        request.setPrice(updateRequest.getPrice());
        Category temp = new Category();
        temp.setTitle(updateRequest.getCategory());
        request.setCategory(temp);
        request.setDescription(updateRequest.getDescription());
        request.setImageUrl(updateRequest.getImage());

        return productService.updateProduct(productId, request);
    }

    // 4. delete a product
    @DeleteMapping("/products/{Id}")
    public Product deleteProduct(@PathVariable("Id") Long productId){
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
    @GetMapping("/products/categories/{category}")
    public List<Product> getAllProductsCategoryWise(@PathVariable("category") String category){
        return productService.getAllProductsCategoryWise(category);
    }
}
