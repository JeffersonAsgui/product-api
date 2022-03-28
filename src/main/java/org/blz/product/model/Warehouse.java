package org.blz.product.model;

import lombok.*;
import org.blz.product.enuns.WarehouseType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_warehouse", schema = "api_product_db")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "locality", nullable = false)
    private String locality;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @EqualsAndHashCode.Include
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WarehouseType type;

    @ManyToOne(targetEntity = Product.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @EqualsAndHashCode.Include
    @Column(name = "product_id", nullable = false,
            insertable = false, updatable = false)
    private Long product_id;

}