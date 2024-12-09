package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.UserDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import org.springframework.data.domain.Page

interface AdminService {
    fun fetchUsers(page:Int,size:Int):ApiResponse<Page<UserDto>>
}