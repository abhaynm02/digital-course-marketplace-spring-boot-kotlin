package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.SignupResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignInRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignupRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.handleResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody request: SignupRequest):ResponseEntity<Any>{
        return handleResponse(authService.signUp(request))
    }
    @PostMapping("/signin")
    fun signIn(@Valid @RequestBody request: SignInRequest):ResponseEntity<Any>{
        return handleResponse(authService.singIn(request))
    }
}