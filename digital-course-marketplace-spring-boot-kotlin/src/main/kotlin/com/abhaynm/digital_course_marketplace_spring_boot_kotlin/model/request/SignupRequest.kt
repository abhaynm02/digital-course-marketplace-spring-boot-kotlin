package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlin.math.min

data class SignupRequest(
    @field:NotBlank(message = "Name is required")
    @field:Size(min=2, max = 40 , message = "Name must be between 2 and 50 characters")
    val name:String,
    @field:Email(message = "Invalid email format")
    @field:NotBlank(message = "Email is required")
    val email:String,
    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    val password:String,
    val role:Role
)

