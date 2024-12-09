package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto

import java.time.LocalDate

data class PurchaseDto(
    val purchaseId:Long,
    val courseTitle:String,
    val purchaseDate:LocalDate
)
