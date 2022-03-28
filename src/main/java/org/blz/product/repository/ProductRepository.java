package org.blz.product.repository;

import org.blz.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product obj WHERE obj.sku = :sku")
    Optional<Product> findBySku(@Param("sku") Long cpf);
}
