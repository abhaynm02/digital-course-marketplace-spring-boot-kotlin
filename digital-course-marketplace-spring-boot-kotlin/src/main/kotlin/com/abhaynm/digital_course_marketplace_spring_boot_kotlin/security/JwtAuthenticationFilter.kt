package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
     private val tokenProvider: TokenProvider,
    private val myUserDetailsService: MyUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // getting auth header form request
        val authHeader =request.getHeader("Authorization")
        if (authHeader ==null || !authHeader.startsWith("Bearer ")){
            //performing filter
            filterChain.doFilter(request,response)
            return
        }
        //getting token from authHeader
        val token =authHeader.substring(7)

        try {
            //extracting the username from token
            val username =tokenProvider.extractUsername(token)

            if (username  != null && SecurityContextHolder.getContext().authentication ==null){
                val userDetails =myUserDetailsService.loadUserByUsername(username)
                //validating the jwt token
                if (tokenProvider.isValid(token,userDetails)){
                    val authToken =UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    authToken.details =WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication=authToken
                }
            }
        }catch (e: ExpiredJwtException) {
            logger.warn("Expired JWT token: ${e.message}")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has expired")
            return
        } catch (e: SignatureException) {
            logger.warn("Invalid JWT signature: ${e.message}")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token signature")
            return
        }

         filterChain.doFilter(request,response)
    }
}