package com.example.noofense.Configs;


import com.example.noofense.Exceptions.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class MVCSecurityConfig  {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(configurer ->
                configurer
                        .requestMatchers("/css/**", "/scripts/**", "/images/**", "/registration/**", "/api/users/**").permitAll() // Разрешить доступ к статическим ресурсам
                        .anyRequest().authenticated()

                ).formLogin(login ->
                        login
                                .loginPage("/registration")
                                .loginProcessingUrl("/auth")
                                .defaultSuccessUrl("/main/bids", true)
                                .failureHandler(customAuthenticationFailureHandler)
                                .permitAll())
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/registration")
                                .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}