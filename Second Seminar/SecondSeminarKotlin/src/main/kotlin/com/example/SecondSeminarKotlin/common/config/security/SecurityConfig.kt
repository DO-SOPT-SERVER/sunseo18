package com.example.SecondSeminarKotlin.common.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

private const val AUTH_WHITELIST_SIGNUP = "/api/users/sign-up"
private const val AUTH_WHITELIST_SIGNIN = "/api/users/sign-in"

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customJwtAuthenticationEntryPoint: CustomJwtAuthenticationEntryPoint
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(customJwtAuthenticationEntryPoint)
            .accessDeniedHandler(customAccessDeniedHandler)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(AUTH_WHITELIST_SIGNUP, AUTH_WHITELIST_SIGNIN).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }
}