package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedCustomer

interface UpdateCustomerNameByIdUseCase {

    suspend fun updateCustomerNameById(customerId: Long, name: String): UpdatedCustomer
}