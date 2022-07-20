package com.KingSlayer.ecommerce.controller;

import com.KingSlayer.ecommerce.global.GlobalData;
import com.KingSlayer.ecommerce.model.Cart;
import com.KingSlayer.ecommerce.model.Product;
import com.KingSlayer.ecommerce.model.Role;
import com.KingSlayer.ecommerce.service.CartService;
import com.KingSlayer.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") int id) {
        Product product = productService.findById(id);

        GlobalData.cart.add(product);
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeItemFromCart(@PathVariable("index") int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
}
