package com.spring.security.service;

import com.spring.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMemoryService {

    public List<UserDetails> users(){

        UserDetails u1 = new User("Aliyu","1234","ADMIN");
        UserDetails u2 = new User("Yasir","1234","MANAGER");

        return List.of(u1,u2);
    }
}
