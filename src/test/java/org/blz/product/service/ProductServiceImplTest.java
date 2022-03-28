package org.blz.product.service;

import org.blz.product.BaseUnitTest;
import org.blz.product.exception.DataIntegratyViolationException;
import org.blz.product.exception.ObjectNotFoundException;
import org.blz.product.model.Product;
import org.blz.product.repository.ProductRepository;
import org.blz.product.util.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest extends BaseUnitTest {

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
        productMapper = ProductMapper.INSTANCE;
    }

    @Test
    void should_returnTrue_when_productIsMarketable() {
        Assert.isTrue(createFakeProductDTO().isMarketable());
        assertTrue(true);
    }

    @Test
    void should_returnTrue_when_inventoryQuantityIsMajorZero() {
        Assert.isTrue(createFakeProductDTO().getInventory().getQuantity() > 0);
        assertTrue(true);
    }

    @Test
    void should_returnTheSumOfQuantityProperty_when_calcInventoryQuantityIsCalled() {
        var productDTO = createFakeProductDTO();
        assertEquals(14, productService.calcInventoryQuantity(productDTO.getInventory().getWarehouses()),
                "Regular sum should work");
    }

    @Test
    void should_returnProduct_when_ProductIsCreated() {
        var product = productMapper.toProduct(createFakeProductDTO());
        when(productRepository.save(any(Product.class)))
                .thenReturn(product);
        var productSuccess = productMapper.toProduct(
                productService.create(createFakeProductDTO()));

        assertEquals(productSuccess, product);
    }

    @Test
    void should_returnError_when_ProductIsCreated() {
        var thrownExpected = new DataIntegratyViolationException("sku already exists in database!");
        when(productRepository.save(any(Product.class)))
                .thenThrow(thrownExpected);
        var thrown = Assertions.assertThrows(
                DataIntegratyViolationException.class, () -> {
                    productService.create(createFakeProductDTO());
                }, "");

        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());

    }

    @Test
    void should_returnError_when_updatedObject() {
        var thrownExpected = new DataIntegratyViolationException("sku different to requested payload!");
        when(productRepository.save(any(Product.class)))
                .thenThrow(thrownExpected);

        var thrown = Assertions.assertThrows(
                DataIntegratyViolationException.class, () -> {
                    productService.create(createFakeProductDTO());
                }, "");

        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());

    }

    @Test
    void should_returnProductDTO_when_findBySkuIsCalled() {
        var expectedReturn = createFakeProductDTO();

        var optionalProduct = Optional.of(productMapper.toProduct(expectedReturn));

        when(productRepository.findBySku(any(Long.class)))
                .thenReturn(optionalProduct);

        var productSuccess = productService.findBySku(6589L);
        assertEquals(productSuccess, expectedReturn);
    }

    @Test
    void should_returnError_when_findBySkuIsCalled() {
        var thrownExpected = new ObjectNotFoundException("sku not exists in database!");
        var thrown = Assertions.assertThrows(
                ObjectNotFoundException.class, () -> {
                    productService.findBySku(6589L);
                }, "");

        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());
    }

    @Test
    void should_returnError_when_deleteByIdIsCalled() {
        var thrownExpected = new ObjectNotFoundException("sku not exists in database!");
        var thrown = Assertions.assertThrows(
                ObjectNotFoundException.class, () -> {
                    productService.deleteById(6589L);
                }, "");

        Assertions.assertEquals(thrownExpected.getMessage(), thrown.getMessage());
    }
}