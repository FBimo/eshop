package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getItemId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setItemId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public void delete(String productId) {
        productData.removeIf(product -> product.getItemId().equals(productId));
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getItemId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit (String productId, Product updatedProduct) {
        for (Product product : productData) {
            if (product.getItemId().equals(productId)) {
                product.setItemName(updatedProduct.getItemName());
                product.setItemQuantity(updatedProduct.getItemQuantity());
                return product;
            }
        }
        return null;
    }
}
