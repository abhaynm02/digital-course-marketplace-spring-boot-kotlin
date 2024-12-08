package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "userentity",uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("email"))])
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    @Column(name = "name")
    val name:String,
    @Column(name="email")
    val email:String,
    @JsonIgnore
    @Column(name="password")
    val passWord:String,
    @Enumerated(EnumType.STRING)
    val role: Role
) : UserDetails{
    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =  mutableListOf(SimpleGrantedAuthority(this.role.name))

    override fun getPassword(): String =this.passWord
    @JsonIgnore
    override fun getUsername(): String =this.email
    @JsonIgnore
    override fun isAccountNonExpired() = true

    @JsonIgnore
    override fun isAccountNonLocked() = true

    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    @JsonIgnore
    override fun isEnabled() = true

}
