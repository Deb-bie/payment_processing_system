package com.example.services;


import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        String email = oauth2User.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    System.out.println(oauth2User);
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setFirstName(oauth2User.getAttribute("given_name"));
                    newUser.setLastName(oauth2User.getAttribute("family_name"));
                    newUser.setProvider(provider);
                    newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return userRepository.save(newUser);
                });

        return oauth2User;
    }
}
