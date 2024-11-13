package com.example.noofense.Controllers;

import com.example.noofense.Models.UserDto;
import com.example.noofense.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("add")
    public ResponseEntity<String> addUser(UserDto user) {
        ResponseEntity<String> response = userServices.userAdd(user);
        return response;
    }
}
