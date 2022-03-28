package org.blz.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Min(0)
    private Integer quantity = 0;

    @NotNull(message = "Inventory warehouses is required, can not be null")
    @JsonProperty("warehouses")
    private List<WarehouseDTO> warehouses;

}