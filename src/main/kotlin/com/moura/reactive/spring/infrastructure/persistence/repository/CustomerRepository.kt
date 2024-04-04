package com.moura.reactive.spring.infrastructure.persistence.repository

import com.moura.reactive.spring.infrastructure.persistence.entity.CustomerEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository : CoroutineCrudRepository<CustomerEntity, Long> {

    @Query(
        """
            UPDATE reactive_spring_schema.customers
            SET name = ?1
            WHERE reactive_spring_schema.customers.id == ?2
        """
    )
    suspend fun updateNameById(name: String, customerId: Long)

    @Query(
        """
            UPDATE reactive_spring_schema.customers
            SET email = ?1
            WHERE reactive_spring_schema.customers.id == ?2
        """
    )
    suspend fun updateEmailById(email: String, customerId: Long)
}