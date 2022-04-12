package org.blz.product.service;

import org.blz.product.dto.ProductDTO;
import org.blz.product.dto.WarehouseDTO;
import org.blz.product.exception.DataIntegratyViolationException;
import org.blz.product.exception.ObjectNotFoundException;
import org.blz.product.model.Product;
import org.blz.product.repository.ProductRepository;
import org.blz.product.util.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isMarketable(Integer quantity) {
        return quantity > 0;
    }

    @Override
    public int calcInventoryQuantity(List<WarehouseDTO> warehouseDTOList) {
        return warehouseDTOList.stream()
                .mapToInt(WarehouseDTO::getQuantity)
                .reduce(Integer::sum).getAsInt();
    }

    @Override
    @Transactional
    public ProductDTO create(ProductDTO productRequest) {
        if (productRepository.findBySku(productRequest.getSku()).isPresent())
            new DataIntegratyViolationException("sku already exists in database!");

        var product = productMapper.toProduct(productRequest);
        return productMapper.toProductDTO(productRepository.save(setProductId(product)));

    }

    @Transactional
    @Override
    public ProductDTO update(Long sku, ProductDTO productRequest) {
        validationSku(sku, productRequest);
        var productUpdate = productMapper.toProduct(productRequest);
        return productMapper.toProductDTO(productRepository.save(setProductId(productUpdate)));
    }

    @Override
    @Cacheable(cacheNames = "Product")
    public ProductDTO findBySku(Long sku) {
        var productDTO = productMapper.toProductDTO(verifyIfExists(sku));
        productDTO.getInventory().setQuantity(calcInventoryQuantity(productDTO.getInventory().getWarehouses()));
        productDTO.setMarketable(isMarketable(productDTO.getInventory().getQuantity()));
        return productDTO;
    }


    @Transactional
    @Override
    public void deleteById(Long sku) {
        productRepository.deleteById(verifyIfExists(sku).getId());
    }

    private Product verifyIfExists(Long sku) {
        logger.info("m=verifyIfExists in productRepository sku={}", sku);
        return productRepository.findBySku(sku)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("sku not exists in database!"));
    }

    private Product setProductId(Product product) {
        product.getWarehouses().forEach(o -> o.setProduct(product));
        return product;
    }

    private void validationSku(Long sku, ProductDTO productRequest) {
        if (!productRequest.getSku().equals(sku))
            new DataIntegratyViolationException("sku different to requested payload!");
    }
}
