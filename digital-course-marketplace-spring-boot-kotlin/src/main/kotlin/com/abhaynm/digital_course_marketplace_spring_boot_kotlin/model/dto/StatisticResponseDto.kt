package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto

import org.springframework.data.domain.Page

data class StatisticResponseDto(
    val totalAmount:Long,
    val courseDto:  Page<CourseDto>
)
