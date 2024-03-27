package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.usecases.DeleteCustomerUseCase

class DeleteCustomerInteractor(
    private val customerGateway: CustomerGateway,
) : DeleteCustomerUseCase {

    override suspend fun deleteCustomerById(id: Long) {
        return customerGateway.deleteCustomerById(id)
    }
}