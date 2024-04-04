package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.usecases.GetCustomerByIdUseCase
import com.moura.reactive.spring.domain.entity.Customer

class GetCustomerByIdInteractor(
    private val customerGateway: CustomerGateway,
) : GetCustomerByIdUseCase {

    override suspend fun getCustomerById(customerId: Long): Customer {
        return customerGateway.getCustomerById(customerId)
    }
}