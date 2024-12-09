package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.exception

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.method.MethodValidationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.DateTimeException

//global exception handler for handle spring frame work exception
@RestControllerAdvice
class GlobalExceptionHandler {
    //handling validation error globally
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException):ResponseEntity<ApiResponse.Error.ValidationError<Any>>{
        val errors = ex.bindingResult.fieldErrors
            .associate { it.field to (it.defaultMessage ?: "Invalid value") }
        return ResponseEntity.badRequest().body(ApiResponse.Error.ValidationError(errors))

    }

    @ExceptionHandler(DateTimeException::class)
    fun  handleDateTimeException(ex:DateTimeException):ResponseEntity<ApiResponse.Error.DateInvalidError<String>>{
        return ResponseEntity.badRequest().body(ApiResponse.Error.DateInvalidError("Invalid Date"))
    }

}