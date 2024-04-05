package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedCustomer
import com.moura.reactive.spring.domain.entity.Customer

fun interface UpdateCustomerByIdUseCase {

    suspend fun updateCustomerById(customerId: Long, customer: Customer): UpdatedCustomer
}