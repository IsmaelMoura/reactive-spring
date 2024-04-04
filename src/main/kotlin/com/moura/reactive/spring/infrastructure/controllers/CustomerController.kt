package com.moura.reactive.spring.infrastructure.controllers

import com.moura.reactive.spring.application.usecases.CreateCustomerUseCase
import com.moura.reactive.spring.application.usecases.DeleteCustomerUseCase
import com.moura.reactive.spring.application.usecases.GetAllCustomersUseCase
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.controllers.dto.customer.CreateCustomerRequest
import com.moura.reactive.spring.infrastructure.controllers.dto.customer.CreateCustomerResponse
import com.moura.reactive.spring.infrastructure.controllers.dto.customer.CustomerDTOMapper
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Endpoints.CUSTOMERS_ENDPOINT)
class CustomerController(
    private val customerDTOMapper: CustomerDTOMapper,
    private val createCustomerUseCase: CreateCustomerUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase,
    private val getAllCustomersUseCase: GetAllCustomersUseCase,
) {


    @PostMapping
    suspend fun createCustomer(
        @RequestBody createCustomerRequest: CreateCustomerRequest,
    ): ResponseEntity<CreateCustomerResponse> {
        logger.info { "Received POST request for create new customer. createCustomerRequest: [$createCustomerRequest]" }

        return customerDTOMapper.toDomainCustomer(createCustomerRequest)
            .let { customer -> createCustomerUseCase.createCustomer(customer) }
            .let { customer -> customerDTOMapper.toResponse(customer) }
            .let { response -> ResponseEntity.ok(response) }
    }

    @DeleteMapping("/{customerId}")
    suspend fun deleteCustomerById(@PathVariable customerId: Long): ResponseEntity<Unit> {
        logger.info { "Received DELETE request for delete customer by id. customerId: [$customerId]" }

        return deleteCustomerUseCase.deleteCustomerById(customerId)
            .let { ResponseEntity.noContent().build() }
    }

    @GetMapping
    fun getAllCustomers(): Flow<Customer> {
        logger.info { "Received GET request for all customers." }

        return getAllCustomersUseCase.getAllCustomers()

    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}