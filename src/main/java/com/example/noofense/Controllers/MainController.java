package com.example.noofense.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserController userController;

    public MainController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/registration")
    public String greeting(Model model) {
        return "registration";
    }

    @GetMapping("/main")
    public String mainForm(Model model) {
        return "main";
    }



}
