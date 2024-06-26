package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Customer
import kotlinx.coroutines.flow.Flow

fun interface GetAllCustomersUseCase {

    fun getAllCustomers(): Flow<Customer>
}