package com.teachercrudapp.TeacherCurdApp.config;

import com.teachercrudapp.TeacherCurdApp.service.CustomTeacherDetails;
import com.teachercrudapp.TeacherCurdApp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        String[] staticResource = {
                "/templates/**",
                "/static/**"
        };

        http
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/login", "/register").permitAll()
                                .requestMatchers(staticResource).permitAll()
                                .anyRequest().authenticated()
                ).formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/welcome", true)
                                .loginPage("/login")
                                .failureUrl("/login?error")
                                .permitAll()
                ).logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setUserDetailsService(customUserDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .requestMatchers("/css/**", "/images/**");
    }

}
