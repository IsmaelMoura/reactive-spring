package com.moura.reactive.spring.main.config

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.interactors.CreateCustomerInteractor
import com.moura.reactive.spring.application.interactors.DeleteCustomerInteractor
import com.moura.reactive.spring.application.interactors.GetAllCustomersInteractor
import com.moura.reactive.spring.application.interactors.GetCustomerByIdInteractor
import com.moura.reactive.spring.application.usecases.CreateCustomerUseCase
import com.moura.reactive.spring.application.usecases.DeleteCustomerUseCase
import com.moura.reactive.spring.application.usecases.GetAllCustomersUseCase
import com.moura.reactive.spring.application.usecases.GetCustomerByIdUseCase
import com.moura.reactive.spring.infrastructure.controllers.dto.customer.CustomerDTOMapper
import com.moura.reactive.spring.infrastructure.gateways.CustomerRepositoryGateway
import com.moura.reactive.spring.infrastructure.gateways.mapper.CustomerEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerConfig {

    @Bean
    fun createCustomerUseCase(customerGateway: CustomerGateway): CreateCustomerUseCase {
        return CreateCustomerInteractor(customerGateway = customerGateway)
    }

    @Bean
    fun getAllCustomersUseCase(customerGateway: CustomerGateway): GetAllCustomersUseCase {
        return GetAllCustomersInteractor(
            customerGateway = customerGateway
        )
    }

    @Bean
    fun getCustomerByIdUseCase(customerGateway: CustomerGateway): GetCustomerByIdUseCase {
        return GetCustomerByIdInteractor(
            customerGateway = customerGateway
        )
    }

    @Bean
    fun deleteCustomerUseCase(customerGateway: CustomerGateway): DeleteCustomerUseCase {
        return DeleteCustomerInteractor(
            customerGateway = customerGateway
        )
    }

    @Bean
    fun customerGateway(
        productRepository: CustomerRepository,
        customerEntityMapper: CustomerEntityMapper,
    ): CustomerGateway {
        return CustomerRepositoryGateway(
            customerRepository = productRepository,
            customerEntityMapper = customerEntityMapper
        )
    }

    @Bean
    fun customerEntityMapper(): CustomerEntityMapper {
        return CustomerEntityMapper()
    }

    @Bean
    fun customerDTOMapper(): CustomerDTOMapper {
        return CustomerDTOMapper()
    }
}