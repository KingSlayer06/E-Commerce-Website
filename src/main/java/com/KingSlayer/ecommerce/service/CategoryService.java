package com.KingSlayer.ecommerce.service;

import com.KingSlayer.ecommerce.model.Category;
import com.KingSlayer.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }
    @Transactional
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
