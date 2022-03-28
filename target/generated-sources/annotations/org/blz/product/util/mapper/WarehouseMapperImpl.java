package org.blz.product.util.mapper;

import javax.annotation.processing.Generated;
import org.blz.product.dto.WarehouseDTO;
import org.blz.product.dto.WarehouseDTO.WarehouseDTOBuilder;
import org.blz.product.model.Warehouse;
import org.blz.product.model.Warehouse.WarehouseBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T17:37:19-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Azul Systems, Inc.)"
)
public class WarehouseMapperImpl implements WarehouseMapper {

    @Override
    public WarehouseDTO toDto(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTOBuilder warehouseDTO = WarehouseDTO.builder();

        warehouseDTO.locality( warehouse.getLocality() );
        warehouseDTO.quantity( warehouse.getQuantity() );
        warehouseDTO.type( warehouse.getType() );

        return warehouseDTO.build();
    }

    @Override
    public Warehouse toModel(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.locality( warehouseDTO.getLocality() );
        warehouse.quantity( warehouseDTO.getQuantity() );
        warehouse.type( warehouseDTO.getType() );

        return warehouse.build();
    }
}
