package com.moura.reactive.spring.infrastructure.controllers.dto.user

import com.moura.reactive.spring.domain.entity.Customer

class CustomerDTOMapper {

    fun toDomainCustomer(createCustomerRequest: CreateCustomerRequest) : Customer {
        return Customer(
            name = createCustomerRequest.name,
            email = createCustomerRequest.email
        )
    }

    fun toResponse(customer: Customer) : CreateCustomerResponse {
        return CreateCustomerResponse(
            name = customer.name
        )
    }
}