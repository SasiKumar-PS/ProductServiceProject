package dev.sasikumar.productserviceproject.services;

import dev.sasikumar.productserviceproject.repository.projections.ProductProjection;
import dev.sasikumar.productserviceproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingProductService {
    private final ProductRepository productRepository;
    public CheckingProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public ProductProjection getCheck(Long productId){
        return productRepository.findByIdEquals(productId);
    }

    public List<ProductProjection> getProductsWithCategory(Long categoryId){
        return  productRepository.findProductsByCategory_Id(categoryId);
    }
}
