package org.demo.projectmanagement.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 9:38 AM
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**")
                       .authenticated()
                       .anyRequest()
                       .permitAll()
               )
               .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
               .csrf(AbstractHttpConfigurer::disable)
               .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://localhost:3000", "http://localhost:5173"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setExposedHeaders(Collections.singletonList("Authorization"));
            return config;
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
