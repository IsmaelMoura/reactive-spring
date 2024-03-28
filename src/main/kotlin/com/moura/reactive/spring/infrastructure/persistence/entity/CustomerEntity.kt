package com.moura.reactive.spring.infrastructure.persistence.entity

import com.moura.reactive.spring.infrastructure.persistence.Database
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable

@Table(
    name = Database.Tables.CUSTOMERS,
    schema = Database.Schemas.REACTIVE_SPRING_SCHEMA
)
data class CustomerEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val email: String,
) : Serializable
