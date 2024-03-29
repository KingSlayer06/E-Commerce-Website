package com.KingSlayer.ecommerce.service;

import com.KingSlayer.ecommerce.configuration.CustomUserDetails;
import com.KingSlayer.ecommerce.model.User;
import com.KingSlayer.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("UserName not found!"));

        return user.map(CustomUserDetails::new).get();
    }
}
