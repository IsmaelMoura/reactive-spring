package com.moura.reactive.spring.infrastructure.persistence.entity

import com.moura.reactive.spring.infrastructure.persistence.Database
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal

@Table(
    name = Database.Tables.PRODUCTS,
    schema = Database.Schemas.REACTIVE_SPRING_SCHEMA
)
data class ProductEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val price: BigDecimal,
) : Serializable
