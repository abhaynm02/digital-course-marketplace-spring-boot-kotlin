package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.handleResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val courseService: CourseService
) {
    @GetMapping("/courses")
    fun getCourses(
        @RequestParam(required = false)search:String?,
        @RequestParam(defaultValue = "0")page:Int,
        @RequestParam(defaultValue = "10")size:Int
    ):ResponseEntity<Any>{
        return handleResponse(courseService.getCourses(search,page, size))
    }
}