package com.KingSlayer.ecommerce.controller;

import com.KingSlayer.ecommerce.model.Category;
import com.KingSlayer.ecommerce.model.Product;
import com.KingSlayer.ecommerce.service.CategoryService;
import com.KingSlayer.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Admin Home Page //
    @GetMapping("/home")
    public String adminHome() {
        return "adminHome";
    }

    // Admin Categories Page //
    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/categories/add")
    public String getCategories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/categories/add")
    public String addCategories(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    // Admin Products Page //
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/add")
    public String getProducts(Model model) {
        model.addAttribute("product", new Product());
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String addProducts(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }
}
