package com.KingSlayer.ecommerce.configuration;

import com.KingSlayer.ecommerce.model.Role;
import com.KingSlayer.ecommerce.model.User;
import com.KingSlayer.ecommerce.repository.RoleRepository;
import com.KingSlayer.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain chain, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        String email = token.getPrincipal().getAttributes().get("email").toString();
        String firstName = token.getPrincipal().getAttributes().get("given_name").toString();
        String lastName = token.getPrincipal().getAttributes().get("family_name").toString();

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());

        if (!userRepository.findUserByEmail(email).isPresent()) {
            User user = User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .roles(roles)
                    .build();

            userRepository.save(user);
        }

        redirectStrategy.sendRedirect(request, response, "/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

    }
}
