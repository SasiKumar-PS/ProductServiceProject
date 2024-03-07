package dev.sasikumar.scalerproject.services;

import dev.sasikumar.scalerproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.scalerproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.scalerproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.scalerproject.exceptions.NotValidCategoryException;
import dev.sasikumar.scalerproject.exceptions.ProductNotFoundException;
import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    Product createProduct(CreateProductRequestDTO request);

    Product updateProduct(Long productId, UpdateProductRequestDTO request);

    Product deleteProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException;
}
