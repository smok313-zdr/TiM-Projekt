package com.example.shop.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserDetailsService {
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
