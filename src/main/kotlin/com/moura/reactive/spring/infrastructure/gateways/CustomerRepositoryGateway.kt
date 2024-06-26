package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.common.exceptions.CustomerNotFoundException
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.gateways.mapper.CustomerEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    override fun getAllCustomers(): Flow<Customer> {
        return customerRepository.findAll()
            .map { entity -> customerEntityMapper.toDomainObject(entity) }
            .onEach { customer -> logger.info { "Found customer: [$customer] on database" } }
    }

    override suspend fun getCustomerById(customerId: Long): Customer {
        return customerRepository.findById(customerId)
            ?.let { customerEntityMapper.toDomainObject(it) }
            ?: throwCustomerNotFoundException(customerId)
    }

    override suspend fun deleteCustomerById(id: Long) {
        customerRepository.deleteById(id)
    }

    private fun throwCustomerNotFoundException(customerId: Long): Nothing {
        logger.warn { "Customer with id: [$customerId] was not found" }

        throw CustomerNotFoundException(customerId)
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}