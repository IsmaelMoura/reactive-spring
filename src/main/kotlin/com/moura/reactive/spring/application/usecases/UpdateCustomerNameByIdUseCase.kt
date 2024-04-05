package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedCustomer

fun interface UpdateCustomerNameByIdUseCase {

    suspend fun updateCustomerNameById(customerId: Long, name: String): UpdatedCustomer
}