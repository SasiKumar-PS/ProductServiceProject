package dev.sasikumar.scalerproject.controllers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.sasikumar.scalerproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.scalerproject.models.Product;
import dev.sasikumar.scalerproject.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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



    // create a product - done
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

    // get a single product - done
    @GetMapping("/products/{Id}")
    public Product getSingleProductDetails(@PathVariable("Id") Long productId) {
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/products")
    public void getAllProducts() {

    }

    // update a product - need to work
    @PutMapping("/product/{Id}")
    public void updateProduct(@PathVariable("Id") Long productId) {
        // gets productId to update
        // make a put request, and send a request body to make the updates
        // save the returned object and return as project model
    }


    // delete a product - need to work (implemented same as get a product)
    @DeleteMapping("/products/{Id}")
    public Product deleteProduct(@PathVariable("Id") Long productId){
        return productService.deleteProduct(productId);
    }
}
