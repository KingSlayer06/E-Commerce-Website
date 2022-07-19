package com.KingSlayer.ecommerce.controller;

import com.KingSlayer.ecommerce.dto.ProductDTO;
import com.KingSlayer.ecommerce.model.Category;
import com.KingSlayer.ecommerce.model.Product;
import com.KingSlayer.ecommerce.service.CategoryService;
import com.KingSlayer.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/product_images";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Admin Home Page //
    @GetMapping({"/","/home"})
    public String adminHome() {
        return "adminHome";
    }

    // Admin Categories Section //
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

    @GetMapping("/categories/delete/{id}")
    public String deleteCategories(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategories(@PathVariable("id") int id, Model model) {
        Category category = categoryService.findById(id);

        if (category != null)
            model.addAttribute("category", category);

        return "categoriesAdd";
    }

    // Admin Products Section //
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/add")
    public String getProducts(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.findAll());
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String addProducts(@ModelAttribute("product") ProductDTO productDTO,
                              @RequestParam("productImage") MultipartFile file,
                              @RequestParam("imgName") String imgName) throws IOException {

        // Upload image to directory
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, imageUUID);
            Files.write(filePath, file.getBytes());
        }
        else {
            imageUUID = imgName;
        }

        Product product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .category(categoryService.findById(productDTO.getCategoryId()))
                .price(productDTO.getPrice())
                .weight(productDTO.getWeight())
                .description(productDTO.getDescription())
                .imageName(imageUUID)
                .build();

        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProducts(@PathVariable("id") int id) throws IOException {

        String imageUUID = productService.findById(id).getImageName();
        Path filePath = Paths.get(uploadDirectory, imageUUID);
        Files.delete(filePath);

        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateProducts(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);

        if (product != null) {

            ProductDTO productDTO = ProductDTO.builder()
                                    .id(product.getId())
                                    .name(product.getName())
                                    .categoryId(product.getCategory().getId())
                                    .price(product.getPrice())
                                    .weight(product.getWeight())
                                    .description(product.getDescription())
                                    .imageName(product.getImageName())
                                    .build();

            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("product", productDTO);
        }

        return "productsAdd";
    }
}