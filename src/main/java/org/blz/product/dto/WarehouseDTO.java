package org.blz.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blz.product.enuns.WarehouseType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO implements Serializable {

    @NotEmpty(message = "Warehouse locality is required, can not be null")
    @JsonProperty("locality")
    private String locality;

    @NotEmpty(message = "Warehouse quantity is required, can not be empty")
    @JsonProperty("quantity")
    private Integer quantity;

    @NotEmpty(message = "Warehouse type is required, can not be empty")
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private WarehouseType type;

    @JsonIgnore
    private Long product_sku;
}
