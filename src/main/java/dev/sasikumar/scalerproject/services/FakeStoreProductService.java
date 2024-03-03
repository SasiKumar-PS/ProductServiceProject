package dev.sasikumar.scalerproject.services;

import dev.sasikumar.scalerproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.scalerproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.scalerproject.models.Category;
import dev.sasikumar.scalerproject.models.Product;
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
    public Product createProduct(String title,
                                 double price,
                                 String category,
                                 String description,
                                 String image) {

        FakeStoreProductsDTO request = new FakeStoreProductsDTO();
        request.setTitle(title);
        request.setPrice(price);
        request.setCategory(category);
        request.setDescription(description);
        request.setImage(image);

        FakeStoreProductsDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,    //request body*
                FakeStoreProductsDTO.class
        );

        if(response == null) return new Product();
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductsDTO fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductsDTO.class
        );
        if(fakeStoreProduct == null) return new Product();
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product updateProduct(Long productId, Product request) {

        UpdateProductRequestDTO updateRequest = new UpdateProductRequestDTO();
        updateRequest.setTitle(request.getTitle());
        updateRequest.setPrice(request.getPrice());
        updateRequest.setCategory(request.getCategory().getTitle());
        updateRequest.setDescription(request.getDescription());
        updateRequest.setImage(request.getImageUrl());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                updateRequest
        );

        return request;
    }


    @Override
    public Product deleteProduct(Long productId) {
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
        Product deletedProduct = getSingleProduct(productId);

        if(deletedProduct == null) return new Product();
        return deletedProduct;
    }

    @Override
    public List<Product> getAllProducts() {

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                List.class
        );
        List<Product> allProducts = new ArrayList<>();

        for(LinkedHashMap<String, String> product : response){
            Product tempProduct = new Product();
            tempProduct.setId(Long.valueOf (String.valueOf(product.get("id")) ));
            tempProduct.setTitle(product.get("title"));
            tempProduct.setDescription(product.get("description"));
            tempProduct.setPrice(Double.parseDouble (String.valueOf(product.get("price")) ));
            tempProduct.setImageUrl(product.get("image"));

            Category temp = new Category();
            temp.setTitle(product.get("category"));
            tempProduct.setCategory(temp);

            allProducts.add(tempProduct);
        }

        return allProducts;
    }

    @Override
    public List<Category> getAllCategories() {
        List<String> allCategoryList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                List.class
        );

        List<Category> allcategory = new ArrayList<>();
        for(String category : allCategoryList) {
            Category tempCategory = new Category();
            tempCategory.setTitle(category);
            allcategory.add(tempCategory);
        }

        return allcategory;
    }

    @Override
    public List<Product> getAllProductsCategoryWise(String category) {

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                List.class
        );

        List<Product> allProducts = new ArrayList<>();

        for(LinkedHashMap<String, String> product : response){
            Product tempProduct = new Product();
            tempProduct.setId(Long.valueOf (String.valueOf(product.get("id")) ));
            tempProduct.setTitle(product.get("title"));
            tempProduct.setDescription(product.get("description"));
            tempProduct.setPrice(Double.parseDouble (String.valueOf(product.get("price")) ));
            tempProduct.setImageUrl(product.get("image"));

            Category temp = new Category();
            temp.setTitle(product.get("category"));
            tempProduct.setCategory(temp);

            allProducts.add(tempProduct);
        }

        return allProducts;
    }
}
