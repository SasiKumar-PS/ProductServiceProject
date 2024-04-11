package dev.sasikumar.productserviceproject.services;

import dev.sasikumar.productserviceproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.productserviceproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.productserviceproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.productserviceproject.exceptions.NotValidCategoryException;
import dev.sasikumar.productserviceproject.exceptions.ProductNotFoundException;
import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;
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
    public Long deleteProduct(Long productId) throws ProductNotFoundException {
        Product deletedProduct = getSingleProduct(productId);
        if(deletedProduct == null){
            throw new ProductNotFoundException("Product with Id: '" + productId + "' is not a valid product id");
        }

        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
        return productId;
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
