package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.PurchaseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.StatisticResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import org.springframework.data.domain.Page
import java.time.LocalDate

interface PurchaseService {
    fun purchaseCourse(courseId:Long):ApiResponse<PurchaseDto>
    fun getStatistics(page:Int ,
                      size:Int,
                      startDate:LocalDate,
                      endDate: LocalDate
    ):ApiResponse<StatisticResponseDto>
}