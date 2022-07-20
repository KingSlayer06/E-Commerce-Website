package com.KingSlayer.ecommerce.controller;

import com.KingSlayer.ecommerce.model.Role;
import com.KingSlayer.ecommerce.model.User;
import com.KingSlayer.ecommerce.repository.RoleRepository;
import com.KingSlayer.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               HttpServletRequest request)
                        throws ServletException {
        // Encrypt password with BCryptPasswordEncoder
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());

        user.setRoles(roles);
        userRepository.save(user);

        // Login user after being registered
        request.login(user.getEmail(), password);
        return "redirect:/";
    }
}
