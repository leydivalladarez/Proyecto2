package edu.espe.contable.config;

import edu.espe.contable.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/oauth2/**", "/oauth2callback").permitAll()
                                .requestMatchers("/oauth2callback/**").permitAll()
                                .requestMatchers("/api/v1/**").permitAll()
                                .requestMatchers("/chat","/chat/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("http://localhost:3000/login")
                                .defaultSuccessUrl("http://localhost:3000/dashboard", true)
                                .failureUrl("http://localhost:3000/login?error=true")
                                .permitAll()
                );
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/dashboard", true)
//                                .permitAll()
//                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}