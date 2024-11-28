package com.example.noofense.Controllers;

import com.example.noofense.Models.UserDto;
import com.example.noofense.Services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserController userController;

    public MainController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "registration";
    }
    @GetMapping("/main")
    public String mainForm(Model model) {
        return "main";
    }



}
