package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "course",uniqueConstraints = [UniqueConstraint(columnNames =["title"])])
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "price")
    val price: Double,
    @Column(name = "created_date")
    val cratedDate :LocalDate,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val createdBy :UserEntity,

)
