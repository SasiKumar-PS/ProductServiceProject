package dev.sasikumar.productserviceproject.repository;

import dev.sasikumar.productserviceproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    List<Category> findAll();
}
