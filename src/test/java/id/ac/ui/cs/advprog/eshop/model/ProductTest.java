package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setItemName("Produk apa aja");
        this.product.setItemQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getItemId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Produk apa aja", this.product.getItemName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getItemQuantity());
    }
}
