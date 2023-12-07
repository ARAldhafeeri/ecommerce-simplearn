
package com.org.ecommerce.config;
import com.org.ecommerce.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final UsersService userService;

    public SecurityConfig(UsersService userService) {
        this.userService = userService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/user/*").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/admin/*").permitAll()
                .requestMatchers("/admin/").permitAll()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().cacheControl();

        return http.build();
    }

}