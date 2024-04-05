package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Customer

fun interface GetCustomerByIdUseCase {

    suspend fun getCustomerById(customerId: Long): Customer
}