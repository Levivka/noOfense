package com.example.noofense.Configs;

import com.example.noofense.Services.Impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServicesImpl userServicesImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || username.isBlank()) {

            throw new AuthenticationException("Имя пользователя не может быть пустым") {};
        }
        if (password == null || password.isBlank()) {
            throw new AuthenticationException("Пароль не может быть пустым") {};
        }

        if (userServicesImpl.login(username, password).getStatusCode().value() == 200) {
            UserDetails userDetails = User.withUsername(username)
                    .password(password) // TODO: добавить шифрование
                    .roles("USER")
                    .build();

            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new AuthenticationException("Некорректный логин или пароль") {};
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

