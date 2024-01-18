package com.moura.reactive.spring.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customer")
data class CustomerEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val email: String
)
