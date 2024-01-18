package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Customer

interface CreateCustomerUseCase {

    suspend fun createCustomer(customer: Customer): Customer
}