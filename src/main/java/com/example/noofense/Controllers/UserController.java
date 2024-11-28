package com.example.noofense.Controllers;

import com.example.noofense.Models.UserDto;
import com.example.noofense.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("list")
    public ResponseEntity<String> getUserList() {
        ResponseEntity<String> response = userServices.userList();
        return response;
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("confirmPassword") String confirmPas,
            @ModelAttribute("fio") String fio,
            @ModelAttribute("email") String email
    ) {
        if (!validatePasswords(password, confirmPas, model)) {
            return "registration";
        }

        int statusCode = userServices.registration(username, password, confirmPas, email).getStatusCode().value();
        return handleResponseStatus(statusCode, model); // Действие в зависимости от статуса
    }

    private boolean validatePasswords(String password, String confirmPas, Model model) {
        if (!password.equals(confirmPas)) {
            model.addAttribute("error", "Пароли не совпадают");
            return false;
        }
        return true;
    }

    private String handleResponseStatus(int statusCode, Model model) {
        return switch (statusCode) {
            case 201 -> "redirect:/";
            case 400 -> {
                model.addAttribute("error", "Такой пользователь уже существует");
                yield "registration";
            }
            case 500 -> {
                model.addAttribute("error", "Ошибка на сервере, попробуйте ещё раз чуть позже");
                yield "registration";
            }
            default -> {
                model.addAttribute("error", "Неизвестная ошибка");
                yield "registration";
            }
        };
    }


    @PostMapping("/login")
    public String login(
            Model model,
            @ModelAttribute("username")
            String username,
            @ModelAttribute("password")
            String password,
            @ModelAttribute("confirmPassword")
            String confirmPas
    ) {
        if (!validatePasswords(password, confirmPas, model)) {
            return "registration";
        }
        if (userServices.login(username, password).getStatusCode().value() == 200) {
            return "redirect:/main";
        }
        else {
            model.addAttribute("error", "Некорректный пароль или логин");
            return "registration";
        }
    }
}
