package com.example.noofense.Controllers;

import com.example.noofense.Models.User;
import com.example.noofense.Services.Impl.UserServicesImpl;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserServicesImpl userServicesImpl;

    @Autowired
    public UserController(UserServicesImpl userServicesImpl) {
        this.userServicesImpl = userServicesImpl;
    }

    @GetMapping("list")
    public ResponseEntity<String> getUserList() {
        ResponseEntity<String> response = userServicesImpl.userList();
        return response;
    }

    @PostMapping()
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

        int statusCode = userServicesImpl.registration(username, password, confirmPas, email).getStatusCode().value();
        return handleResponseStatus(statusCode, model);
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
            case 201 -> "redirect:/registration";
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
        if (userServicesImpl.login(username, password).getStatusCode().value() == 200) {
            return "redirect:/main/bids";
        }
        else {
            model.addAttribute("error", "Некорректный пароль или логин");
            return "registration";
        }
    }
}
