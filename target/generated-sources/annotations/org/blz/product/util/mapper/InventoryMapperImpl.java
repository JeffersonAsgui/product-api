package org.blz.product.util.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.blz.product.dto.InventoryDTO;
import org.blz.product.dto.InventoryDTO.InventoryDTOBuilder;
import org.blz.product.dto.WarehouseDTO;
import org.blz.product.model.Inventory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T15:21:06-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Azul Systems, Inc.)"
)
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryDTO toDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryDTOBuilder inventoryDTO = InventoryDTO.builder();

        List<WarehouseDTO> list = inventory.getWarehouses();
        if ( list != null ) {
            inventoryDTO.warehouses( new ArrayList<WarehouseDTO>( list ) );
        }
        inventoryDTO.quantity( inventory.getQuantity() );

        return inventoryDTO.build();
    }
}
