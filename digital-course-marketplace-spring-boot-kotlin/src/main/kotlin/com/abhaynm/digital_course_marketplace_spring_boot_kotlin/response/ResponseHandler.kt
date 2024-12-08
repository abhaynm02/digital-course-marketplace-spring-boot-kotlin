package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun handleResponse(response: ApiResponse<*>): ResponseEntity<Any> {
        return when (response){
            is ApiResponse.Success->ResponseEntity.ok(response)
            is ApiResponse.Error.EmailAlreadyExists->ResponseEntity.status(HttpStatus.CONFLICT).body(response)
            is ApiResponse.Error.ValidationError->ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            is ApiResponse.Error.UnexpectedError ->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
            is ApiResponse.Error.InvalidCredentials ->ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
            is ApiResponse.Error.CourseTitleExists ->ResponseEntity.status(HttpStatus.CONFLICT).body(response)
        }
    }


