package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignInRequest(
    @field:Email(message = "Invalid username format")
    @field:NotBlank(message = "Username is required")
    val username:String,
    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    val password:String
)
