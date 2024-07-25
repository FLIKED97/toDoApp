package com.example.To_Do.List.Application.service;

import com.example.To_Do.List.Application.models.User;
import com.example.To_Do.List.Application.repository.UserRepository;
import com.example.To_Do.List.Application.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(s);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new CustomUserDetails(user.get());
    }
}
