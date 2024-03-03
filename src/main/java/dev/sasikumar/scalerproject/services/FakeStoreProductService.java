package dev.sasikumar.scalerproject.services;

import dev.sasikumar.scalerproject.DTOs.FakeStoreProductsDTO;
import dev.sasikumar.scalerproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductsDTO fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductsDTO.class
        );

        return fakeStoreProduct.toProduct();
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

        return response.toProduct();
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long productId) {
        // for put, I think it's exactly same as create Product
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        // I used getForObject, exactly same as get
        FakeStoreProductsDTO deletedProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductsDTO.class
        );
        return deletedProduct.toProduct();
    }
}
