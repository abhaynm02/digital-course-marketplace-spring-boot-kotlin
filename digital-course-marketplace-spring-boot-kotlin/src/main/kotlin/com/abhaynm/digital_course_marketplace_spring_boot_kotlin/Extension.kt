package com.abhaynm.digital_course_marketplace_spring_boot_kotlin

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.CourseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Course
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.CreateCourseRequest
import org.springframework.security.core.context.SecurityContextHolder
import java.time.LocalDate

fun CreateCourseRequest.toCourse()=Course(
    id = null,
    title=this.title,
    description=this.description,
    price=this.price,
    cratedDate = LocalDate.now(),
    createdBy = SecurityContextHolder.getContext().authentication.principal as? UserEntity
        ?: throw IllegalArgumentException("Authenticated user not found")
)
fun Course.toCourseDto()=CourseDto(
    title=this.title,
    description=this.description,
    price=this.price,
    cratedDate,
    createdBy = this.createdBy.name
)