package dev.sasikumar.scalerproject.services;

import dev.sasikumar.scalerproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    Product createProduct(String title,
                          double price,
                          String category,
                          String description,
                          String image);

    List<Product> getProducts();

    Product updateProduct(Long productId);
    Product deleteProduct(Long productId);
}
