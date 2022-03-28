package org.blz.product.util.mapper;

import org.blz.product.dto.ProductDTO;
import org.blz.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {InventoryMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "inventory.warehouses", target = "warehouses")
    Product toProduct(ProductDTO productDTO);

    @Mapping(source = "product.warehouses", target = "inventory.warehouses")
    ProductDTO toProductDTO(Product product);
}
