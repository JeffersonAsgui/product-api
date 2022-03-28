package org.blz.product.util.mapper;

import org.blz.product.dto.WarehouseDTO;
import org.blz.product.model.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductMapper.class})
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

//    @Mapping(source = "product.id", target = "product_id")
    WarehouseDTO toDto(Warehouse warehouse);

    Warehouse toModel(WarehouseDTO warehouseDTO);
}
