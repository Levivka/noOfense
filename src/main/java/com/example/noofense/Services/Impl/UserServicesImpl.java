package com.example.noofense.Services.Impl;

import com.example.noofense.Models.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServicesImpl {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8083/users";

    public UserServicesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> userList() {
        String url = baseUrl + "/list";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<?> login(String username, String password) {
        String url = baseUrl + "/login";
        ResponseEntity<?> res;

        UserDto userDto = new UserDto();
        userDto.setLogin(username);
        userDto.setPassword(password);

        try {
            res = restTemplate.postForEntity(url, userDto, String.class);
        }
        catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Такого пользователя нету");
        }
        return res;
    }

    public ResponseEntity<?> registration(String username, String password, String email, String fio) {
        String url = baseUrl + "/add";
        ResponseEntity<?> res;
        UserDto user = new UserDto();
        user.setLogin(username);
        user.setPassword(password);
        user.setFio(fio);
        user.setEmail(email);

        try {
            res = restTemplate.postForEntity(url, user, String.class);
        }
        catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (HttpServerErrorException.InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return res;
    }
}
