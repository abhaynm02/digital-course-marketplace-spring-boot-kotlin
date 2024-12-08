package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Course
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface CourseRepository :JpaRepository<Course,Long> {
    abstract fun existsByTitle(title: String): Boolean
    @Query("SELECT c FROM Course c WHERE c.createdBy.email = :username")
    fun findByCreatedByEmail(@Param("username") username:String,page:Pageable): Page<Course>

    @Query(
        "SELECT c FROM Course c WHERE " +
                "(:search IS NULL OR c.title LIKE %:search% OR c.description LIKE %:search%)"
    )
    fun findByTitleOrDescription(@Param("search") search: String?, pageable: Pageable?): Page<Course>
}