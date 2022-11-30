package com.example.firstspringbootsecurityapp.controllers.user;


import com.example.firstspringbootsecurityapp.models.User;
import com.example.firstspringbootsecurityapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userPage() {
        return "user";
    }

}