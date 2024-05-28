package dev.sasikumar.productserviceproject.services;

import dev.sasikumar.productserviceproject.exceptions.NotValidCategoryException;
import dev.sasikumar.productserviceproject.exceptions.ProductNotFoundException;
import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    Product createProduct(String title, Double price, String category, String description, String image);

    Product updateProduct(Long productId, String title, Double price, String category, String description, String image) throws ProductNotFoundException;

    Long deleteProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException;
}
