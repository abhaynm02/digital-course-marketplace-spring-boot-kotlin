package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.imp

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.exception.ResourceNotFoundException
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.SignInResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.dto.SignupResponseDto
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignInRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.request.SignupRequest
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.repository.UserRepository
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.response.ApiResponse
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.security.TokenProvider
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.service.AuthService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImp(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val authenticationManager: AuthenticationManager
) :AuthService{
    override fun signUp(request: SignupRequest): ApiResponse<SignupResponseDto> {
        return try {
            //checking the email is already in use
            if (userRepository.existsByEmail(request.email)) {
                ApiResponse.Error.EmailAlreadyExists(msg = "The given email is already registered")

            } else {

                val user = UserEntity(
                    id = null,
                    name = request.name,
                    email = request.email,
                    passWord = passwordEncoder.encode(request.password),
                    role = request.role
                )
                //saving the use in database
                val savedUser = userRepository.save(user)
                val response = SignupResponseDto(
                    name = savedUser.name,
                    username = savedUser.email
                )
                // returning api success response
                ApiResponse.Success(
                    data = response,
                    message = "Account created successfully"
                )
            }
        } catch (e: Exception) {
            ApiResponse.Error.UnexpectedError(msg = "An unexpected error occurred during registration")
        }
    }

    override fun singIn(request: SignInRequest): ApiResponse<SignInResponseDto> {
        return try {
            //checking password and username
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username,request.password))
            //fetching logged user details form repository
            val user =userRepository.findByEmail(request.username)?: throw ResourceNotFoundException("User with email ${request.username} not found")
           //generating jwt token
            val token =tokenProvider.generateToken(user)
            val response =SignInResponseDto(
                name = user.name,
                token=token
            )
            ApiResponse.Success("success",response)
            //if the username or password is invalid this exception will throw
        }catch (e:AuthenticationException){

            ApiResponse.Error.InvalidCredentials("Invalid credentials")
        }


    }
}