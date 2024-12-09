package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.handleResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.PurchaseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/stats")
class StatsController(
    private val purchaseService: PurchaseService
) {
    @GetMapping
    fun getCourseStats(
        @RequestParam(defaultValue = "0")page:Int,
        @RequestParam(defaultValue = "10")size:Int,
        @RequestParam(required = false) startDate: String?,
        @RequestParam(required = false) endDate: String?
    ):ResponseEntity<Any>{
        val start = startDate?.let { LocalDate.parse(it) } ?: LocalDate.MIN
        val end = endDate?.let { LocalDate.parse(it) } ?: LocalDate.MAX
        return handleResponse(purchaseService.getStatistics(page,size,start,end))
    }
}