package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @GetMapping("/greet")
    fun greetingFromAdmin():ResponseEntity<String>{
        val user= SecurityContextHolder.getContext().authentication.principal
        println(user)
        return ResponseEntity.ok("hey you success fully hit admin controller ")
    }
}