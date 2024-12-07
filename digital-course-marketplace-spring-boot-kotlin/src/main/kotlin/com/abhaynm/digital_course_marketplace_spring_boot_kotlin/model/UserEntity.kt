package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
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
    val password:String,
    @Enumerated(EnumType.STRING)
    val role:Role
)
