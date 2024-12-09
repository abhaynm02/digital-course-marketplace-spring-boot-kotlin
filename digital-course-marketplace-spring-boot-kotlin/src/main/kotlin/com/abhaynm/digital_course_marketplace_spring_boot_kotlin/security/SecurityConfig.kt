package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.security

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.LocalDateTime

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val myUserDetailsService: MyUserDetailsService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        //for accessing the h2 console
          http.headers { obj: HeadersConfigurer<HttpSecurity> ->
              obj.frameOptions { obj1 ->
                  obj1.disable()
              }
          }
         http
             //disabling  the custom security
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            //securing the api end point based on roll for authorization
            .authorizeHttpRequests {
                it.requestMatchers(PathRequest.toH2Console()).permitAll()
                it.requestMatchers("/api/auth/**","/api/home/**","/api/stats").permitAll()
                it.requestMatchers("/api/admin/**","/api/stats").hasAuthority(Role.ADMIN.name)
                it.requestMatchers("/api/customer/**","/api/stats").hasAuthority(Role.CUSTOMER.name)
                it.requestMatchers("/api/creator/**").hasAuthority(Role.CREATOR.name)
                it.anyRequest().authenticated()
            }
            //applying the jwt filter to the spring security to achieve the jwt authentication
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
              return http.build()
    }
     //authenticationManager bean for verifying user details
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authenticationConfiguration.authenticationManager
    }
   //passwordEncoder bean for encoding user password
    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}