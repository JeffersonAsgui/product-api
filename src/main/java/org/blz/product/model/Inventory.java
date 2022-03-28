package org.blz.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blz.product.dto.WarehouseDTO;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {

    private Integer quantity;

    private List<WarehouseDTO> warehouses;
}
