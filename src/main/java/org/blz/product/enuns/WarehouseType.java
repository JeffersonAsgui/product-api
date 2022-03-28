package org.blz.product.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarehouseType {

    ECOMMERCE("ECOMMERCE"),
    PHYSICAL_STORE("PHYSICAL_STORE");

    private final String description;
}