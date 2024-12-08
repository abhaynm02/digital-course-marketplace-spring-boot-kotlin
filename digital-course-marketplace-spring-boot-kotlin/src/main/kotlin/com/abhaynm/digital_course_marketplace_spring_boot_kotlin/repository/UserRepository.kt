package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<UserEntity,Long> {
    abstract fun findByEmail(username: String?): UserEntity?
    fun existsByEmail(email: String): Boolean
}