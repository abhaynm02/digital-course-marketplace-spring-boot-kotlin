package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.handleResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminService: AdminService
) {
   @GetMapping("/users")
   fun getUsers(
       @RequestParam(defaultValue = "0")page:Int,
       @RequestParam(defaultValue = "10")size:Int
   ):ResponseEntity<Any>{
       return handleResponse(adminService.fetchUsers(page,size))
   }
}