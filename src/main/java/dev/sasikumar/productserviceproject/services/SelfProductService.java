package dev.sasikumar.productserviceproject.services;

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
    public Product createProduct(String title, Double price, String category, String description, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category productCategory = categoryRepository.findByTitle(category);
        if(productCategory == null){
            productCategory = new Category();
            productCategory.setTitle(category);
        }
        product.setCategory(productCategory);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, String title, Double price, String category, String description, String image) throws ProductNotFoundException {
        Product product = getSingleProduct(productId);  //throws exception, if product is not available

        product.setId(productId);
        if(title != null){
            product.setTitle(title);
        }
        if(price != null){
            product.setPrice(price);
        }
        if(description != null){
            product.setDescription(description);
        }
        if(image != null){
            product.setImageUrl(image);
        }
        if(category != null){
            product.getCategory().setTitle(title);
        }

        Category productCategory = categoryRepository.findByTitle(category);
        if(productCategory == null){
            productCategory = new Category();
            productCategory.setTitle(category);
        }
        product.setCategory(productCategory);
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
