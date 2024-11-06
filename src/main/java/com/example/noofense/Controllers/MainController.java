package com.example.noofense.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Hello World");
        return "registration";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Hello World");
        return "registration";
    }

}
