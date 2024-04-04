package com.moura.reactive.spring.infrastructure.persistence.repository

import com.moura.reactive.spring.infrastructure.persistence.entity.ProductEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductRepository : CoroutineCrudRepository<ProductEntity, Long> {

    @Query(
        """
            UPDATE reactive_spring_schema.products 
            SET name = ?1 
            WHERE reactive_spring_schema.products.id == ?2
        """
    )
    suspend fun updateNameById(email: String, productId: Long)

    @Query(
        """
            UPDATE reactive_spring_schema.products 
            SET name = ?1 
            WHERE reactive_spring_schema.products.id == ?2
        """
    )
    suspend fun updatePriceById(name: String, customerId: Long)

}