package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.CourseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.CreateCourseRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.handleResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/creator")
class CreatorController(
    private val courseService: CourseService
) {
    @PostMapping("/create/course")
    fun createCourse(@Valid @RequestBody request: CreateCourseRequest):ResponseEntity<Any> {
       return handleResponse(courseService.createCourse(request))
    }
    @GetMapping("/get/courses")
    fun getCoursesByUser(
        @RequestParam(defaultValue = "0") page:Int,
        @RequestParam(defaultValue = "10")size:Int
    ):ResponseEntity<Any>{
        return handleResponse(courseService.findCoursesForLoggedUser(page,size))
    }
}