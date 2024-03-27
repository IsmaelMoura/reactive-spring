package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.usecases.GetAllCustomersUseCase
import com.moura.reactive.spring.domain.entity.Customer
import kotlinx.coroutines.flow.Flow

class GetAllCustomersInteractor(
    private val customerGateway: CustomerGateway,
) : GetAllCustomersUseCase {

    override fun getAllCustomers(): Flow<Customer> {
        return customerGateway.getAllCustomers()
    }
}