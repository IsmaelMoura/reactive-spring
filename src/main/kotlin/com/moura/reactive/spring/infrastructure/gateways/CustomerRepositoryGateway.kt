package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.gateways.mapper.CustomerEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KotlinLogging

class CustomerRepositoryGateway(
    private val customerRepository: CustomerRepository,
    private val customerEntityMapper: CustomerEntityMapper,
) : CustomerGateway {

    override suspend fun createCustomer(customer: Customer): Customer {
        return customerEntityMapper.toEntity(customer)
            .let { customerRepository.save(it) }
            .also { logger.info { "Customer ${it.id} successfully created" } }
            .let { customerEntityMapper.toDomainObject(it) }
    }

    override suspend fun deleteCustomerById(id: Long) {
        return customerRepository.deleteById(id)
    }

    override fun getAllCustomers(): Flow<Customer> {
        return customerRepository.findAll()
            .map { entity -> customerEntityMapper.toDomainObject(entity)}
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}