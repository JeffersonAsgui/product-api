package org.blz.product.resource;

import org.blz.product.dto.ProductDTO;
import org.blz.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductResource {

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productRequest) {
        logger.info("m=product.create sku={}", productRequest.getSku());
        var productDTO = productService.create(productRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sku}")
                .buildAndExpand(productDTO.getSku()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @PutMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> update(
            @PathVariable Long sku,
            @Valid @RequestBody ProductDTO productRequest) {
        logger.info("m=product.update sku={}", productRequest.getSku());
        productService.deleteById(sku);
        var productDTO = productService.update(sku, productRequest);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> findById(@PathVariable("sku") Long sku) {
        logger.info("m=product.findBySku sku={}", sku);
        ProductDTO productDTO = productService.findBySku(sku);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("sku") Long sku) {
        logger.info("m=product.deleteBy sku={}", sku);
        productService.deleteById(sku);
    }

}