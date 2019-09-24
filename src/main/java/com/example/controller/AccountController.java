package com.example.controller;

import com.example.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AccountController {
    private final UserDetailsService userDetailsService;

    public AccountController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("login", new UserDTO());
        return "login";
    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login() {
//
//        return "login";
//    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {


        return "register";
    }
}
