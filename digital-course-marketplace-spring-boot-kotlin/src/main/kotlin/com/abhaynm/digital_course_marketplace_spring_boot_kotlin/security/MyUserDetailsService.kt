package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.security

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.exception.ResourceNotFoundException
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class MyUserDetailsService (private val userRepository: UserRepository):UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username)?: throw ResourceNotFoundException("User with email $username not found")
    }
}