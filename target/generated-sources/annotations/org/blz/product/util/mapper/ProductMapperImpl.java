package org.blz.product.util.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.blz.product.dto.InventoryDTO;
import org.blz.product.dto.InventoryDTO.InventoryDTOBuilder;
import org.blz.product.dto.ProductDTO;
import org.blz.product.dto.ProductDTO.ProductDTOBuilder;
import org.blz.product.dto.WarehouseDTO;
import org.blz.product.dto.WarehouseDTO.WarehouseDTOBuilder;
import org.blz.product.model.Product;
import org.blz.product.model.Product.ProductBuilder;
import org.blz.product.model.Warehouse;
import org.blz.product.model.Warehouse.WarehouseBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T17:37:19-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Azul Systems, Inc.)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        List<WarehouseDTO> warehouses = productDTOInventoryWarehouses( productDTO );
        product.warehouses( warehouseDTOListToWarehouseList( warehouses ) );
        product.sku( productDTO.getSku() );
        product.name( productDTO.getName() );

        return product.build();
    }

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.inventory( productToInventoryDTO( product ) );
        productDTO.sku( product.getSku() );
        productDTO.name( product.getName() );

        return productDTO.build();
    }

    private List<WarehouseDTO> productDTOInventoryWarehouses(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }
        InventoryDTO inventory = productDTO.getInventory();
        if ( inventory == null ) {
            return null;
        }
        List<WarehouseDTO> warehouses = inventory.getWarehouses();
        if ( warehouses == null ) {
            return null;
        }
        return warehouses;
    }

    protected Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        WarehouseBuilder warehouse = Warehouse.builder();

        warehouse.locality( warehouseDTO.getLocality() );
        warehouse.quantity( warehouseDTO.getQuantity() );
        warehouse.type( warehouseDTO.getType() );

        return warehouse.build();
    }

    protected List<Warehouse> warehouseDTOListToWarehouseList(List<WarehouseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Warehouse> list1 = new ArrayList<Warehouse>( list.size() );
        for ( WarehouseDTO warehouseDTO : list ) {
            list1.add( warehouseDTOToWarehouse( warehouseDTO ) );
        }

        return list1;
    }

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTOBuilder warehouseDTO = WarehouseDTO.builder();

        warehouseDTO.locality( warehouse.getLocality() );
        warehouseDTO.quantity( warehouse.getQuantity() );
        warehouseDTO.type( warehouse.getType() );

        return warehouseDTO.build();
    }

    protected List<WarehouseDTO> warehouseListToWarehouseDTOList(List<Warehouse> list) {
        if ( list == null ) {
            return null;
        }

        List<WarehouseDTO> list1 = new ArrayList<WarehouseDTO>( list.size() );
        for ( Warehouse warehouse : list ) {
            list1.add( warehouseToWarehouseDTO( warehouse ) );
        }

        return list1;
    }

    protected InventoryDTO productToInventoryDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        InventoryDTOBuilder inventoryDTO = InventoryDTO.builder();

        inventoryDTO.warehouses( warehouseListToWarehouseDTOList( product.getWarehouses() ) );

        return inventoryDTO.build();
    }
}
