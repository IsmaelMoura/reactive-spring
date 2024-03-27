package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.domain.entity.Customer
import kotlinx.coroutines.flow.Flow

class CustomerDTOMapper {

    fun toDomainCustomer(createCustomerRequest: CreateCustomerRequest): Customer {
        return Customer(
            name = createCustomerRequest.name,
            email = createCustomerRequest.email
        )
    }

    fun toResponse(customer: Customer): CreateCustomerResponse {
        return CreateCustomerResponse(
            name = customer.name
        )
    }
}