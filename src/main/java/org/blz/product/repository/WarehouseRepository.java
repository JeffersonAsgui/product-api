package org.blz.product.repository;

import org.blz.product.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Modifying
    @Query("DELETE FROM Warehouse w WHERE w.product_id = :product_id")
    void deleteByProductId(@Param("product_id") Long product_id);

}
