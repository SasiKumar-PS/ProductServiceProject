package dev.sasikumar.productserviceproject.repository;

import dev.sasikumar.productserviceproject.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
