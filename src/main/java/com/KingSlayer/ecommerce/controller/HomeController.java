package com.KingSlayer.ecommerce.controller;

import com.KingSlayer.ecommerce.global.GlobalData;
import com.KingSlayer.ecommerce.service.CategoryService;
import com.KingSlayer.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/","/home"})
    public String home(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable("id") int id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAllByCategoryId(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }
}
