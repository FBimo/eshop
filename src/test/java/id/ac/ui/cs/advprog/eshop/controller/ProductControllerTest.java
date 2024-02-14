package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private Model model;

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
        String page = productController.deleteProduct(0);
        assertEquals("redirect:../list", page);
    }

    @Test
    void testEditProductPage() {
        when(productService.findById(0)).thenReturn(new Product());
        String page = productController.editProductPage(0, model);
        assertEquals("EditProduct", page);
    }

    @Test
    void testEditProduct() {
        String page = productController.editProduct(0, new Product(), model);
        assertEquals(page, "redirect:../list");
    }
}

