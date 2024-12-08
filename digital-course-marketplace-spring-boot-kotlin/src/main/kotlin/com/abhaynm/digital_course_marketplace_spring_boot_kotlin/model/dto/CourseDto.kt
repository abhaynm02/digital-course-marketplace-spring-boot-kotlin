package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto

import java.time.LocalDate

data class CourseDto(
    val title:String,
    val description:String,
    val price:Double,
    val createdDate:LocalDate,
    val createdBy:String
)
