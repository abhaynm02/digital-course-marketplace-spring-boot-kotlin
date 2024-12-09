package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role

data class UserDto(
    val name:String,
    val username:String,
    val role:Role
)
