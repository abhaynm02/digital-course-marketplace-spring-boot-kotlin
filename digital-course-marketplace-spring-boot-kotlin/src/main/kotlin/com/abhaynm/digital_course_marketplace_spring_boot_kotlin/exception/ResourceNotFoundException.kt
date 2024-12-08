package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
@ResponseStatus(value = HttpStatus.NOT_FOUND)
data class ResourceNotFoundException(
    val msg:String?,
    val httpStatus: HttpStatus =HttpStatus.NOT_FOUND
) : RuntimeException(msg)
