package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.imp

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.PurchaseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.StatisticResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.CustomerLibrary
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.CourseRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.CustomerLibraryRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.UserRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.PurchaseService
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.toCourseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.toPurchaseDto
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PurchaseServiceImp(
    private val customerLibraryRepository:CustomerLibraryRepository,
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
):PurchaseService {
    @Transactional
    override fun purchaseCourse(courseId: Long): ApiResponse<PurchaseDto> {
        return try {
            val course =courseRepository.findById(courseId)
            if (course.isEmpty){
                ApiResponse.Error.CourseIdInvalid("Course not found with ID: $courseId")
            }else {
                val user= SecurityContextHolder.getContext().authentication.principal as UserEntity
                if (customerLibraryRepository.existsByCustomerAndCourse(user,course.get())){
                    ApiResponse.Error.CourseAlreadyPurchased("Course is already in your library")
                }else{
                    val customerLibrary=CustomerLibrary(
                        id = null,
                        customer = user,
                        course = course.get(),
                        purchaseDate = LocalDate.now()
                    )
                    val result =customerLibraryRepository.save(customerLibrary).toPurchaseDto()
                    ApiResponse.Created("Course purchased successfully ",result)

                }
            }
        }catch (e:Exception){
            ApiResponse.Error.UnexpectedError("Unexpected error ")
        }
    }

    override fun getStatistics(page: Int,
                               size: Int,
                               startDate: LocalDate,
                               endDate: LocalDate
                               ): ApiResponse<StatisticResponseDto> {
        val user= SecurityContextHolder.getContext().authentication.principal as UserEntity
        val(finalStart,finalEnd)=if (startDate.isAfter(endDate)){
            endDate to startDate
        }else{
            startDate to endDate
        }
        val pageable = PageRequest.of(page,size)
        return if (user.role == Role.ADMIN){
            success1(finalStart, finalEnd, pageable)
        }else if(user.role== Role.CREATOR){
            success(user, finalStart, finalEnd, pageable)
        }else if (user.role==Role.CUSTOMER){
            success(user, finalStart, finalEnd, pageable)
        }else{
            ApiResponse.Error.UnexpectedError("UnexpectedError ")
        }
        
      
    }

    private fun success1(
        startDate: LocalDate,
        endDate: LocalDate,
        pageable: PageRequest
    ): ApiResponse.Success<StatisticResponseDto> {
        val result = customerLibraryRepository.findByPurchaseDateBetween(startDate, endDate, pageable)
        val response = result.map { it.course.toCourseDto() }
        val totalAmount = response.sumOf { it.price }
        return ApiResponse.Success("", StatisticResponseDto(totalAmount.toLong(), response))
    }

    private fun success(
        user: UserEntity,
        startDate: LocalDate,
        endDate: LocalDate,
        pageable: PageRequest
    ): ApiResponse.Success<StatisticResponseDto> {
        val result =
            customerLibraryRepository.findCourseByUsernameAndDateRange(user.username, startDate, endDate, pageable)
        print(result.content.size)
        val response = result.map { it.toCourseDto() }
        val totalAmount = response.sumOf { it.price.toLong() }
        return ApiResponse.Success("Success", StatisticResponseDto(totalAmount, response))
    }
}