package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public void delete(int productId) {
        productData.remove(productId);
    }

    public Product findById(int productId) {
        return productData.get(productId);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit (int productId, Product newProduct) {
        Product oldProduct = productData.get(productId);
        oldProduct.setProductName(newProduct.getProductName());
        oldProduct.setProductQuantity(newProduct.getProductQuantity());
        return oldProduct;
    }
}
