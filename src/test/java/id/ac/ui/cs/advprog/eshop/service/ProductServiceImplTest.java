package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl service;

    @Test
    public void testDeleteProduct() {
        service.create(new Product());

        List<Product> beforeDeleteProducts = service.findAll();
        System.out.println(beforeDeleteProducts.size());

        assertEquals(beforeDeleteProducts.size(), 1);

        service.delete(0);
        List<Product> afterDeleteProducts = service.findAll();
        assertEquals(afterDeleteProducts.size(), 0);
    }

    @Test
    public void testEditProduct() {
        Product oldProduct = new Product();
        oldProduct.setProductId("eb558e9f-1039-4600-8860-71af6af63bde");
        oldProduct.setProductName("Sampo Cap Bambang");
        oldProduct.setProductQuantity(100);
        service.create(oldProduct);

        Product newProduct = new Product();
        newProduct.setProductName("New Sampo Cap Bambang");
        newProduct.setProductQuantity(50);

        service.edit(0, newProduct);

        Product editedProduct = service.findById(0);

        assertEquals(editedProduct.getProductQuantity(), newProduct.getProductQuantity());
        assertEquals(editedProduct.getProductName(), newProduct.getProductName());

        service.delete(0);
    }

    @Test
    public void testFindAll() {
        service.create(new Product());
        service.create(new Product());
        service.create(new Product());

        List<Product> products = service.findAll();
        assertEquals(3, products.size());

        service.delete(0);
        service.delete(0);
        service.delete(0);
    }

    @Test
    void testCreate() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setProductId("1");
        mockProduct.setProductName("Test Product");
        mockProduct.setProductQuantity(10);

        // Mocking the behavior of the repository
        when(productRepository.create(mockProduct)).thenReturn(mockProduct);

        // Invoking the service method
        Product result = productServiceImpl.create(mockProduct);

        // Assertions
        assertEquals(mockProduct, result);
    }

    @Test
    void testDelete() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setProductId("1");

        // Mocking repository behavior
        when(productRepository.findById(1)).thenReturn(mockProduct);

        // Invoking the service method
        productServiceImpl.delete(1);

        // Verifying interactions
        verify(productRepository).delete(1);
    }

    @Test
    void testFindById() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setProductId("1");

        // Mocking repository behavior
        when(productRepository.findById(1)).thenReturn(mockProduct);

        // Invoking the service method
        Product result = productServiceImpl.findById(1);

        // Verifying the result
        assertEquals(mockProduct, result);
    }

    @Test
    void testEdit() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setProductId("1");

        // Mocking repository behavior
        when(productRepository.edit(1, mockProduct)).thenReturn(mockProduct);

        // Invoking the service method
        Product result = productServiceImpl.edit(1, mockProduct);

        // Verifying interactions
        verify(productRepository).edit(1, mockProduct);

        // Verifying the result
        assertEquals(mockProduct, result);
    }
}
