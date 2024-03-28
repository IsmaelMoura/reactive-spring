package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Customer

fun interface CreateCustomerUseCase {

    suspend fun createCustomer(customer: Customer): Customer
}