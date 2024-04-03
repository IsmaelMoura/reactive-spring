package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.alias.UpdatedCustomer

interface UpdateCustomerEmailByIdUseCase {

    suspend fun updateCustomerEmailById(customerId: Long, email: String): UpdatedCustomer
}