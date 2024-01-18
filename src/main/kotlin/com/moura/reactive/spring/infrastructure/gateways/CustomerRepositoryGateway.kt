package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.gateways.mapper.CustomerEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.CustomerRepository

class CustomerRepositoryGateway(
    private val customerRepository: CustomerRepository,
    private val customerEntityMapper: CustomerEntityMapper
) : CustomerGateway {

    override suspend fun createCustomer(customer: Customer): Customer {
        return customerEntityMapper.toEntity(customer)
            .let { customerRepository.save(it) }
            .let(customerEntityMapper::toDomainObject)
    }
}