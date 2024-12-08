package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.imp

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.CourseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.CreateCourseRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.CourseRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.UserRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.CourseService
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.toCourse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.toCourseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CourseServiceImp(
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) :CourseService{

    override fun createCourse(request: CreateCourseRequest): ApiResponse<CourseDto> {
          return  try {
              if (courseRepository.existsByTitle(request.title)){
                  ApiResponse.Error.CourseTitleExists("The given course title is already used please try another one")
              }else{
                 val course=   courseRepository.save(request.toCourse())
                  ApiResponse.Success("course created successfully",course.toCourseDto())
              }
          }catch (e:Exception){
              ApiResponse.Error.UnexpectedError("An unexpected error occurred during registration")
          }
    }

    override fun findCoursesForLoggedUser(page: Int, size: Int): ApiResponse<Page<CourseDto>> {
        val pageable =PageRequest.of(page,size)
        val user= SecurityContextHolder.getContext().authentication.principal as UserEntity
        val username =user.email
        val course =courseRepository.findByCreatedByEmail(username ,pageable)
        val response =course.map { it.toCourseDto()}
        return ApiResponse.Success("fetch courses ",response)
    }

    override fun getCourses(search: String?, page: Int, size: Int): ApiResponse<Page<CourseDto>> {
        val pageable =PageRequest.of(page,size)
        val courses =courseRepository.findByTitleOrDescription(search,pageable)
        val response =courses.map { it.toCourseDto() }
        return ApiResponse.Success("",response)
    }
}