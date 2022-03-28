package org.blz.product;

import org.blz.product.dto.InventoryDTO;
import org.blz.product.dto.ProductDTO;
import org.blz.product.dto.WarehouseDTO;
import org.blz.product.enuns.WarehouseType;

import java.util.ArrayList;

public class BaseUnitTest {


    protected ProductDTO createFakeProductDTO(){
        var list = new ArrayList<WarehouseDTO>();
        list.add(WarehouseDTO.builder()
                        .quantity(6)
                        .locality("SP")
                        .type(WarehouseType.ECOMMERCE)
                        .build());
        list.add(WarehouseDTO.builder()
                        .quantity(8)
                        .locality("MT")
                        .type(WarehouseType.PHYSICAL_STORE)
                        .build());

        return ProductDTO.builder()
                .name("Product Name")
                .sku(6589L)
                .isMarketable(true)
                .inventory(InventoryDTO.builder()
                        .quantity(14)
                        .warehouses(list).build())
                .build();
    }
}
