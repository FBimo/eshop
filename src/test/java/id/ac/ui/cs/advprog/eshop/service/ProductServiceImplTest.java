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
        Product newProduct = service.create(new Product());
        newProduct.setItemId("0");

        List<Product> beforeDeleteProducts = service.findAll();
        System.out.println(beforeDeleteProducts.size());

        assertEquals(beforeDeleteProducts.size(), 1);

        service.delete("0");
        List<Product> afterDeleteProducts = service.findAll();
        assertEquals(afterDeleteProducts.size(), 0);
    }

    @Test
    public void testEditProduct() {
        Product oldProduct = new Product();
        oldProduct.setItemId("eb558e9f-1039-4600-8860-71af6af63bde");
        oldProduct.setItemName("Sampo Cap Bambang");
        oldProduct.setItemQuantity(100);
        service.create(oldProduct);

        Product newProduct = new Product();
        newProduct.setItemName("New Sampo Cap Bambang");
        newProduct.setItemQuantity(50);

        service.edit("eb558e9f-1039-4600-8860-71af6af63bde", newProduct);

        Product editedProduct = service.findById("eb558e9f-1039-4600-8860-71af6af63bde");

        assertEquals(editedProduct.getItemQuantity(), newProduct.getItemQuantity());
        assertEquals(editedProduct.getItemName(), newProduct.getItemName());

        service.delete("eb558e9f-1039-4600-8860-71af6af63bde");
    }

    @Test
    public void testFindAll() {
        service.create(new Product());
        service.create(new Product());
        service.create(new Product());

        List<Product> products = service.findAll();
        assertEquals(3, products.size());

//        service.delete(0);
//        service.delete(0);
//        service.delete(0);
    }

    @Test
    void testCreate() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setItemId("1");
        mockProduct.setItemName("Test Product");
        mockProduct.setItemQuantity(10);

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
        mockProduct.setItemId("1");

        // Mocking repository behavior
        when(productRepository.findById("1")).thenReturn(mockProduct);

        // Invoking the service method
        productServiceImpl.delete("1");

        // Verifying interactions
        verify(productRepository).delete("1");
    }

    @Test
    void testFindById() {
        // Creating a mock product
        Product mockProduct = new Product();
        mockProduct.setItemId("1");

        // Mocking repository behavior
        when(productRepository.findById("1")).thenReturn(mockProduct);

        // Invoking the service method
        Product result = productServiceImpl.findById("1");

        // Verifying the result
        assertEquals(mockProduct, result);
    }

    @Test
    void testEdit() {
        // Create an instance of the test repository
        ProductRepository testRepository = new ProductRepository();

        // Create an instance of your service and inject the test repository
        ProductService productService = new ProductServiceImpl(testRepository);

        // Create a product and save it using the service
        Product originalProduct = new Product();
        originalProduct.setItemId("1");
        originalProduct.setItemName("Original Product");
        productService.create(originalProduct);

        // Create a modified product
        Product modifiedProduct = new Product();
        modifiedProduct.setItemId("1");
        modifiedProduct.setItemName("Modified Product");

        // Edit the product using the service
        productService.edit("1", modifiedProduct);

        // Retrieve the edited product from the repository
        Product editedProduct = testRepository.findById("1");

        // Assert that the product was successfully edited
        assertEquals("Modified Product", editedProduct.getItemName());
    }
}
