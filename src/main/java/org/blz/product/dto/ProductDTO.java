package org.blz.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    @NotNull(message = "Product sku is required, can not be null")
    @JsonProperty("sku")
    private Long sku;

    @NotEmpty(message = "Product name is required, can not be empty")
    @JsonProperty("name")
    private String name;

    @Valid
    @JsonProperty("inventory")
    private InventoryDTO inventory;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("isMarketable")
    private boolean isMarketable;
}
