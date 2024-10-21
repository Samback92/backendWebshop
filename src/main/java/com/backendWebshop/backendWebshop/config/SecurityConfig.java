// package com.backendWebshop.backendWebshop.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.backendWebshop.backendWebshop.service.MongoUserDetailsService;

// @Configuration
// public class SecurityConfig implements WebMvcConfigurer {

//     private final MongoUserDetailsService mongoUserDetailsService;

//     public SecurityConfig(MongoUserDetailsService mongoUserDetailsService) {
//         this.mongoUserDetailsService = mongoUserDetailsService;
//     }

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOrigins("http://localhost:5173", "https://walrus-app-fyev5.ondigitalocean.app")
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/order").authenticated()
//                 .anyRequest().permitAll())
//             .userDetailsService(mongoUserDetailsService)
//             .formLogin(form -> form
//                 .loginPage("/login")
//                 .defaultSuccessUrl("/", true)
//                 .permitAll())
//             .logout(logout -> logout
//                 .logoutSuccessUrl("/"))
//             .csrf(csrf -> csrf.disable())
//             .cors();
//         return http.build();
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
