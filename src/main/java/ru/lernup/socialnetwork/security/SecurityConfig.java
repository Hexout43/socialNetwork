package ru.lernup.socialnetwork.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfig implements WebFluxConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebFluxConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file//" +uploadPath + "/");
    }

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .authorizeHttpRequests((authz) -> authz
                            .requestMatchers("/user/**").permitAll()
                            .requestMatchers("/user/delete/**").permitAll()
                            .requestMatchers("/orders/**").hasRole("ADMIN")
                            .requestMatchers("/book/**").permitAll()
                            .requestMatchers("/author").authenticated()
                            .anyRequest().authenticated())
                    .formLogin();



            return http.build();

        }




    @Bean
    public BCryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
    }



}
