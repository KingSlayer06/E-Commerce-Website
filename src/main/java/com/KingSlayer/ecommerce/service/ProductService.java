package com.KingSlayer.ecommerce.service;

import com.KingSlayer.ecommerce.model.Category;
import com.KingSlayer.ecommerce.model.Product;
import com.KingSlayer.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }
    @Transactional
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
