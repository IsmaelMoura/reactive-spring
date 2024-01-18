package com.moura.reactive.spring.application.gateways

import com.moura.reactive.spring.domain.entity.Customer

interface CustomerGateway {

    suspend fun createCustomer(customer: Customer): Customer

    suspend fun deleteCustomerById(id: Long)
}