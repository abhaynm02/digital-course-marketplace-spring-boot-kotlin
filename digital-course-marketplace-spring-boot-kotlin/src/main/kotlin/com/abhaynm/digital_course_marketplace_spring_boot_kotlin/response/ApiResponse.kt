package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response

sealed class ApiResponse<T> {
    data class Success<T>(val message:String,val data :T):ApiResponse<T>()

    sealed class Error<T>:ApiResponse<T>(){
        data class ValidationError<T>(val errors:Map<String,String>):Error<T>()
        data class EmailAlreadyExists<T>(val msg:String):Error<T>()
        data class UnexpectedError<T>(val msg: String):Error<T>()
        data class InvalidCredentials<T>(val msg: String):Error<T>()
        data class CourseTitleExists<T>(val msg: String):Error<T>()
    }
}