package com.spring.security.controller;

import com.spring.security.jwtutil.JwtUtil;
import com.spring.security.model.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
public class AuthController {

    Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;



    @ResponseBody
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login-page")
    public String getLogin(@ModelAttribute("user") User user){
        return "login";
    }

    @PostMapping("/login-page")
    public String postLogin(@ModelAttribute("user") User user, Model model, HttpServletResponse response){

        String message = "Invalid Credential";

        logger.info("Username: " + user.getUsername());

        try {
            var usernamePasswordAuthenticationToken =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        }
        catch (Exception e){
            model.addAttribute("message",message);
            return "login";
        }

        String token = jwtUtil.createToken(user.getUsername());
        response.setHeader("Authorization",token);

        return "success";
    }

}
