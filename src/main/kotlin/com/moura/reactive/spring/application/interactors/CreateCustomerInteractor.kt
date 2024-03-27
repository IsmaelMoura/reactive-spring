package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.usecases.CreateCustomerUseCase
import com.moura.reactive.spring.domain.entity.Customer

class CreateCustomerInteractor(
    private val customerGateway: CustomerGateway,
) : CreateCustomerUseCase {

    override suspend fun createCustomer(customer: Customer): Customer {
        return customerGateway.createCustomer(customer)
    }
}