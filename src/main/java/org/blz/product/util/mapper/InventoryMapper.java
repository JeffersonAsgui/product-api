package org.blz.product.util.mapper;

import org.blz.product.dto.InventoryDTO;
import org.blz.product.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {WarehouseMapper.class})
public interface InventoryMapper {

    InventoryMapper INSTANSE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "inventory.warehouses", target = "warehouses")
    InventoryDTO toDto(Inventory inventory);
}
