package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.CourseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.CreateCourseRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import org.springframework.data.domain.Page

interface CourseService {

    fun createCourse( request:CreateCourseRequest):ApiResponse<CourseDto>
    fun findCoursesForLoggedUser(page:Int,size:Int):ApiResponse<Page<CourseDto>>
    fun getCourses(search:String?,page: Int,size: Int):ApiResponse<Page<CourseDto>>
}