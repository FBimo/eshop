package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productlist";
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId) {
        service.delete(productId);
        return "redirect:../list";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") int productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("productId", productId);
        return "editProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProduct(@PathVariable("productId") int productId, @ModelAttribute Product product, Model model) {
        service.edit(productId, product);
        return "redirect:../list";
    }
}
