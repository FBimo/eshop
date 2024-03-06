package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private Model model;

    private List<Product> products;
    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setItemName("Sampo Cap Bambang");
        product1.setItemQuantity(2);
        Product product2 = new Product();
        product2.setItemId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setItemName("Sabun Cap Usep");
        product2.setItemQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateProductPage() {
        String page = productController.createProductPage(model);
        assertEquals(page, "CreateProduct");
    }

    @Test
    void testCreateProductPost() {
        String page = productController.createProductPost(new Product());
        assertEquals(page, "redirect:list");
    }

    @Test
    void testProductListPage() {
        String page = productController.productListPage(model);
        assertEquals("ProductList", page);
    }

    @Test
    void testDeleteProduct() {
        String page = productController.deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("redirect:list", page);
    }

    @Test
    void testEditProductPage() {
        when(productService.findById("a2c62328-4a37-4664-83c7-f32db8620155")).thenReturn(new Product());
        String page = productController.editProductPage("a2c62328-4a37-4664-83c7-f32db8620155", model);
        assertEquals("EditProduct", page);
    }

    @Test
    void testEditProduct() {
        String page = productController.editProductPost(new Product(), model);
        assertEquals(page, "redirect:list");
    }
}

