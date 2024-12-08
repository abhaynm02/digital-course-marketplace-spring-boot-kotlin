package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateCourseRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(min=2, max = 40 , message = "Title must be between 2 and 50 characters")
    val title:String,
    @field:NotBlank(message = "Description is required")
    val description:String,
    @field:NotNull(message = "Price is required")
    @field:Positive(message = "Price must be positive")
    val price:Double,
    val createdDate: LocalDate?,
    val createdBy:String?

)
