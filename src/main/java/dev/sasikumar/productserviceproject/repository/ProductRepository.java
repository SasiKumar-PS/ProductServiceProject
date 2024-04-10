package dev.sasikumar.productserviceproject.repository;

import dev.sasikumar.productserviceproject.models.Product;
import dev.sasikumar.productserviceproject.repository.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);
    Product findByIdIs(Long productId);
    void deleteById(Long productId);
    List<Product> findAll();
    List<Product> findAllByCategoryTitleIs(String category);

    //HQL Query
    @Query("select p.title as title, p.id as id, p.description as description, p.imageUrl as imageUrl from Product p where p.id = :productId")
    ProductProjection findByIdEquals(Long productId);

    // SQL Query
    @Query(value = "select p.title as title, p.id as id from product p where p.category_id = :categoryId", nativeQuery = true)
    List<ProductProjection> findProductsByCategory_Id(@Param("categoryId") Long categoryId);
}
