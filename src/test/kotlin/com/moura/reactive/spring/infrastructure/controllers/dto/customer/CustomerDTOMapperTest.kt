package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.domain.entity.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.random.Random

@UnitTest
class CustomerDTOMapperTest {

    private val underTest = CustomerDTOMapper()

    @Test
    fun toDomainProduct() {
        val name = "product-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val createCustomerRequest = CreateCustomerRequest(name, email)

        val customer = underTest.toDomainCustomer(createCustomerRequest)

        assertThat(customer).isEqualTo(Customer(id = null, name = name, email = email))
    }

    @Test
    fun toResponse() {
        val id = Random.nextLong()
        val name = "product-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val customer = Customer(id = id, name = name, email = email)

        val domainProduct = underTest.toResponse(customer)

        assertThat(domainProduct).isEqualTo(CreateCustomerResponse(id = id, name = name))
    }
}