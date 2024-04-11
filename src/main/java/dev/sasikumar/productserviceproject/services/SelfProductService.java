package dev.sasikumar.productserviceproject.services;

import dev.sasikumar.productserviceproject.DTOs.CreateProductRequestDTO;
import dev.sasikumar.productserviceproject.DTOs.UpdateProductRequestDTO;
import dev.sasikumar.productserviceproject.exceptions.NotValidCategoryException;
import dev.sasikumar.productserviceproject.exceptions.ProductNotFoundException;
import dev.sasikumar.productserviceproject.models.Category;
import dev.sasikumar.productserviceproject.models.Product;
import dev.sasikumar.productserviceproject.repository.CategoryRepository;
import dev.sasikumar.productserviceproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findByIdIs(productId);
        if(product == null){
            throw new ProductNotFoundException("Product with Id: '" + productId + "' is not a valid product id");
        }
        return product;
    }

    @Override
    public Product createProduct(CreateProductRequestDTO request) {
        Product create = request.toProduct();
        Category category = categoryRepository.findByTitle(request.getCategory());
        if(category == null){
            category = new Category();
            category.setTitle(request.getCategory());
        }
        create.setCategory(category);
        return productRepository.save(create);
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductRequestDTO request) throws ProductNotFoundException {
        Product product = getSingleProduct(productId);

        product.setId(productId);
        if(request.getTitle() != null){
            product.setTitle(request.getTitle());
        }
        if(request.getPrice() != null){
            product.setPrice(request.getPrice());
        }
        if(request.getDescription() != null){
            product.setDescription(request.getDescription());
        }
        if(request.getImage() != null){
            product.setImageUrl(request.getImage());
        }
        if(request.getCategory() != null){
            product.getCategory().setTitle(request.getCategory());
        }

        Category category = categoryRepository.findByTitle(request.getCategory());
        if(category == null){
            category = new Category();
            category.setTitle(request.getCategory());
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Long deleteProduct(Long productId) throws ProductNotFoundException {
        Product deletedProduct = productRepository.findByIdIs(productId);
        if(deletedProduct == null){
            throw new ProductNotFoundException("Product with Id: '" + productId + "' is not a valid product id");
        }
        productRepository.deleteById(productId);
        return productId;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException {
        List<Product> allProducts = productRepository.findAllByCategoryTitleIs(category);
        if(allProducts.isEmpty()){
            throw new NotValidCategoryException("Category name: '" + category + "' is not a valid category name");
        }
        return allProducts;
    }
}
