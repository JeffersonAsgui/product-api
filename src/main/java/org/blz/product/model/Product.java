package org.blz.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_product", schema = "api_product_db")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private Long sku;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = Warehouse.class,
            fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "product")
    private List<Warehouse> warehouses = new ArrayList<>();

}