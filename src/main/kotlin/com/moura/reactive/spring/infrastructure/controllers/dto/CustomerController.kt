package com.moura.reactive.spring.infrastructure.controllers.dto

import com.moura.reactive.spring.application.usecases.CreateCustomerUseCase
import com.moura.reactive.spring.infrastructure.controllers.Mappings
import com.moura.reactive.spring.infrastructure.controllers.dto.user.CreateCustomerRequest
import com.moura.reactive.spring.infrastructure.controllers.dto.user.CreateCustomerResponse
import com.moura.reactive.spring.infrastructure.controllers.dto.user.CustomerDTOMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Mappings.CUSTOMERS_PATH)
class CustomerController(
    private val createCustomerUseCase: CreateCustomerUseCase,
    private val customerDTOMapper: CustomerDTOMapper
) {


    @PostMapping
    suspend fun createCustomer(createCustomerRequest: CreateCustomerRequest): ResponseEntity<CreateCustomerResponse> {
        return customerDTOMapper.toDomainCustomer(createCustomerRequest)
            .let { createCustomerUseCase.createCustomer(it) }
            .let { customerDTOMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }
    }
}