package com.abbascoban.gallerist.config;

import com.abbascoban.gallerist.handler.AuthEntryPoint;
import com.abbascoban.gallerist.handler.CustomAccessDeniedHandler;
import com.abbascoban.gallerist.jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";
    public static final String GET_ALL_GALLERISTCAR="/rest/api/gallerist-car/getallgalleristcar";


    private final AuthenticationProvider authenticationProvider;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final AuthEntryPoint authEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests(request->
                        request.requestMatchers(REGISTER , AUTHENTICATE , REFRESH_TOKEN,GET_ALL_GALLERISTCAR,"/uploads/**").permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).accessDeniedHandler(customAccessDeniedHandler).and()
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
