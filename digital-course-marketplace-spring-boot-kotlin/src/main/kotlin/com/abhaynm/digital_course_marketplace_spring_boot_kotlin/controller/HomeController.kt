package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomeController {
    @GetMapping
    fun greet():ResponseEntity<String>{
        return ResponseEntity.ok("hey greetings it is a test for security")
    }
}