package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.security

import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.Role
import com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity.UserEntity
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails

import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider{
    private val secret:String="2b44b0b00fd822d8ce753e54dac3dc4e06c2725f7db930f3b9924468b53194dbccdbe23d7baa5ef5fbc414ca4b2e64700bad60c5a7c45eaba56880985582fba4"
     //extracting the all claims from token
    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
}
    //method for extracting username from token
    fun extractUsername(token: String): String {
        return extractClaim(token) { claims -> claims.subject }
    }
   //method for extracting role from token
    fun extractRole(token: String): Role? {
        return extractClaim(token) { claims ->
            claims["role"]?.toString()?.let { Role.valueOf(it) }
        }
    }
  //validate the token
    fun isValid(token: String, user: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == user.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { claims -> claims.expiration }
    }

    fun <T> extractClaim(token: String, resolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return resolver(claims)
    }
    //generating the jwt token
    fun generateToken(user: UserEntity): String {
        return Jwts.builder()
            .subject(user.username)
            .claim("role", user.role.name)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
            .compact()
    }


}