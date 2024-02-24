package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ItemService<T> {
    T create(T t);
    List<T> findAll();
    T findById(String itemId);
    void edit(String itemId, T item);
    void delete(String itemId);
}
