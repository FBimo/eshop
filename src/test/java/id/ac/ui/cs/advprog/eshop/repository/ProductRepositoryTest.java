package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setItemName("Produk apa aja");
        product.setItemQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getItemId(), savedProduct.getItemId());
        assertEquals(product.getItemName(), savedProduct.getItemName());
        assertEquals(product.getItemQuantity(), savedProduct.getItemQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setItemName("Produk apa aja");
        product1.setItemQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        product2.setItemName("Produk ipi iji");
        product2.setItemQuantity(200);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getItemId(), savedProduct.getItemId());
        savedProduct = productIterator.next();
        assertEquals(product2.getItemId(), savedProduct.getItemId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setItemName("Tester");
        product.setItemQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product.getItemId(), savedProduct.getItemId());
        assertEquals(product.getItemName(), savedProduct.getItemName());
        assertEquals(product.getItemQuantity(), savedProduct.getItemQuantity());
        assertEquals(savedProduct, product);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setItemName("Product dul");
        product.setItemQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getItemId(), savedProduct.getItemId());
        assertEquals(product.getItemName(), savedProduct.getItemName());
        assertEquals(product.getItemQuantity(), savedProduct.getItemQuantity());

        Product editedProduct = new Product();
        editedProduct.setItemName("Product dul Tapi Diedit awokwkwk");
        editedProduct.setItemQuantity(200);
        productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", editedProduct);

        productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product newProduct = productIterator.next();
        assertEquals(newProduct.getItemId(), product.getItemId());
        assertEquals(newProduct.getItemName(), editedProduct.getItemName());
        assertEquals(newProduct.getItemQuantity(), editedProduct.getItemQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setItemName("Product dul");
        product.setItemQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> newProductIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}