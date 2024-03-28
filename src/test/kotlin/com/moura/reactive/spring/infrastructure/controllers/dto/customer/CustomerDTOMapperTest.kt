package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.domain.entity.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

@UnitTest
class CustomerDTOMapperTest {

    private val customerDTOMapper = CustomerDTOMapper()

    @Test
    fun toDomainProduct() {
        val name = "product-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val createCustomerRequest = CreateCustomerRequest(name, email)

        val customer = customerDTOMapper.toDomainCustomer(createCustomerRequest)

        assertThat(customer).isEqualTo(Customer(name, email))
    }

    @Test
    fun toResponse() {
        val name = "product-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val customer = Customer(name, email)

        val domainProduct = customerDTOMapper.toResponse(customer)

        assertThat(domainProduct).isEqualTo(CreateCustomerResponse(name))
    }
}