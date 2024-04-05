package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedCustomer

fun interface UpdateCustomerEmailByIdUseCase {

    suspend fun updateCustomerEmailById(customerId: Long, email: String): UpdatedCustomer
}