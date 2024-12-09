package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Course
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.CustomerLibrary
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CustomerLibraryRepository:JpaRepository<CustomerLibrary,Long> {
    fun existsByCustomerAndCourse(customer :UserEntity,course: Course):Boolean
    @Query("SELECT c.course FROM CustomerLibrary c WHERE c.customer.email = :username AND c.purchaseDate BETWEEN :startDate AND :endDate")
    fun findCourseByUsernameAndDateRange(
        @Param("username")username:String,
        @Param("startDate") startDate:LocalDate,
        @Param("endDate")endDate:LocalDate,
        pageable: Pageable
    ):Page<Course>

    fun findByPurchaseDateBetween(start:LocalDate,
                                  end:LocalDate,
                                  pageable: Pageable
                                  ):Page<CustomerLibrary>
}