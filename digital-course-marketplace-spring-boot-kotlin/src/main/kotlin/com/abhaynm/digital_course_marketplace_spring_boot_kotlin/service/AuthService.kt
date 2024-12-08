package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.SignInResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.SignupResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignInRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignupRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse

interface AuthService {
    fun signUp( request: SignupRequest):ApiResponse<SignupResponseDto>
    fun singIn(request: SignInRequest):ApiResponse<SignInResponseDto>
}