package com.spring.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryUserDetailService implements UserDetailsService {

    private List<UserDetails> users;

    public InMemoryUserDetailService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
