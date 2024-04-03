package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.alias.UpdatedCustomer
import com.moura.reactive.spring.domain.entity.Customer

interface UpdateCustomerByIdUseCase {

    suspend fun updateCustomerById(customerId: Long, customer: Customer): UpdatedCustomer
}