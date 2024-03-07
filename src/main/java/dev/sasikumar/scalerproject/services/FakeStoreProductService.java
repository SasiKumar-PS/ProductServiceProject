package dev.sasikumar.scalerproject.services;

import dev.sasikumar.scalerproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.scalerproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.scalerproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.scalerproject.exceptions.NotValidCategoryException;
import dev.sasikumar.scalerproject.exceptions.ProductNotFoundException;
import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }




    @Override
    public Product createProduct(CreateProductRequestDTO request) {

        FakeStoreProductsDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,    //request body*
                FakeStoreProductsDTO.class
        );

        if(response == null) return new Product();
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductsDTO> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductsDTO.class
        );
        FakeStoreProductsDTO fakeStoreProduct = fakeStoreProductResponse.getBody();

        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("Product with id: "+ productId + " doesn't exist. Please try with a valid product id.");
        }

        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductRequestDTO request) {

        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                request
        );

        return request.toProduct();
    }


    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
        Product deletedProduct = getSingleProduct(productId);

        if(deletedProduct == null) return new Product();
        return deletedProduct;
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductsDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductsDTO[].class
        );
        List<Product> allProducts = new ArrayList<>();

        for(FakeStoreProductsDTO fakeStoreProducts : response){
            Product Product = fakeStoreProducts.toProduct();

            Category temp = new Category();
            temp.setTitle(fakeStoreProducts.getCategory());
            Product.setCategory(temp);

            allProducts.add(Product);
        }

        return allProducts;
    }

    @Override
    public List<Category> getAllCategories() {
        List<String> allCategoryList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                List.class
        );

        List<Category> allCategory = new ArrayList<>();
        for(String category : allCategoryList) {
            Category tempCategory = new Category();
            tempCategory.setTitle(category);
            allCategory.add(tempCategory);
        }

        return allCategory;
    }

    @Override
    public List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException{

        FakeStoreProductsDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductsDTO[].class
        );

        if(response.length == 0){
            throw new NotValidCategoryException("Category name : " + category + " is not valid a category, please provide valid category name.");
        }

        List<Product> allProducts = new ArrayList<>();
        for(FakeStoreProductsDTO product : response){
            allProducts.add(product.toProduct());
        }

        return allProducts;
    }
}
