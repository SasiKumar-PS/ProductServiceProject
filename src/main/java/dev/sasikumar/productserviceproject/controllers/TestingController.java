package dev.sasikumar.productserviceproject.controllers;

import dev.sasikumar.productserviceproject.repository.projections.ProductProjection;
import dev.sasikumar.productserviceproject.services.CheckingProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestingController {
    private final CheckingProductService checkingProductService;
    public TestingController(CheckingProductService checkingProductService){
        this.checkingProductService = checkingProductService;
    }



    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductProjection> getProduct(@PathVariable("productId") Long productId){
        ProductProjection response = checkingProductService.getCheck(productId);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductProjection>> getProducts(@PathVariable("categoryId") Long categoryId){
        List<ProductProjection> allProducts = checkingProductService.getProductsWithCategory(categoryId);
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }

}
