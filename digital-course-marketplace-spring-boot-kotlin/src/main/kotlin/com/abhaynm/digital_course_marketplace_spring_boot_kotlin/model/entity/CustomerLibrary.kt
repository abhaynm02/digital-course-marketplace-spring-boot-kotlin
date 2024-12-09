package com.abhaynm.digital_course_marketplace_spring_boot_kotlin.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "coustomerlibrary")
data class CustomerLibrary(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val customer:UserEntity,
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    val course: Course,
    val purchaseDate: LocalDate
)
