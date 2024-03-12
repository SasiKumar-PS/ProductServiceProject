package dev.sasikumar.productserviceproject.repository;

import dev.sasikumar.productserviceproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);
    Product findByIdIs(Long productId);
    void deleteById(Long productId);
    List<Product> findAll();
    List<Product> findAllByCategoryTitleIs(String category);
}
