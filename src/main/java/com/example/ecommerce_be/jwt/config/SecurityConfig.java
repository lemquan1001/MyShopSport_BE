package com.example.ecommerce_be.jwt.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final UserAuthProvider userAuthProvider;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)//bộ lọc tự tạo ra--> bộ lọc xác thực chính
      .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//bộ lọc cow bản
            .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                            .antMatchers(HttpMethod.POST, "/login", "/register","/forgot-password").permitAll()
                            .antMatchers("/confirm","/getAccountByAdmin/**").permitAll()
                            .antMatchers("/api/**").permitAll()
//                            .antMatchers("/api/productT/**").authenticated()
                            .antMatchers("/logout").authenticated()
//                            .antMatchers("/api/bill/**").authenticated()
//                            .antMatchers("/api/category/**").authenticated()
//                            .antMatchers("/course/**").authenticated()
//                            .antMatchers("/lesson/**").authenticated()
//                            .antMatchers("/comment/**").authenticated()
//                            .antMatchers("/package/**").authenticated()
//                            .antMatchers(HttpMethod.GET, "/student/getAll").authenticated()
                            .anyRequest().authenticated()
            );

    return http.build();
  }

}
