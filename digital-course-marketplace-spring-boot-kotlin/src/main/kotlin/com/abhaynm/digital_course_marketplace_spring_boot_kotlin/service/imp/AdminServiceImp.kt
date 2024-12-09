package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.imp

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.UserDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.UserRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.AdminService
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.toUserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class AdminServiceImp(
    private val userRepository: UserRepository
):AdminService {
    override fun fetchUsers(page:Int,size:Int): ApiResponse<Page<UserDto>> {
        val pageable = PageRequest.of(page,size)
        val roles= listOf(Role.CREATOR,Role.CUSTOMER)
        val users =userRepository.findAllByRoleIn(roles,pageable)
        val response =users.map { it.toUserDto() }
        return ApiResponse.Success("Successfully fetch users",response)

    }
}