package org.blz.product.service;

import org.blz.product.dto.ProductDTO;
import org.blz.product.dto.WarehouseDTO;

import java.util.List;

public interface ProductService {

    boolean isMarketable(Integer quantity);

    int calcInventoryQuantity(List<WarehouseDTO> warehouseDTOList);

    ProductDTO create(ProductDTO productRequest);

    ProductDTO update(Long sku, ProductDTO productRequest);

    ProductDTO findBySku(Long sku);

    void deleteById(Long sku);
}
