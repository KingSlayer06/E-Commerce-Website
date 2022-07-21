package com.KingSlayer.ecommerce.service;

import com.KingSlayer.ecommerce.model.Cart;
import com.KingSlayer.ecommerce.model.Category;
import com.KingSlayer.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Transactional
    public Cart findById(int id) {
        return cartRepository.findById(id).get();
    }

    @Transactional
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
