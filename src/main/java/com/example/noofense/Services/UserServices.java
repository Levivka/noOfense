package com.example.noofense.Services;

import com.example.noofense.Models.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServices {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8083/";

    public UserServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> userList() {
        String url = baseUrl + "users/list";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> userAdd(UserDto user) {
        String url = baseUrl + "users/add";
        return restTemplate.postForEntity(url, user, String.class);
    }
}
