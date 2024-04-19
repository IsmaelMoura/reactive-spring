package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.domain.entity.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.random.Random


class CustomerDTOMapperTest {

    private val underTest = CustomerDTOMapper()

    private var id: Long = 1
    private var name: String = ""
    private var email: String = ""

    @BeforeEach
    fun setUp() {
        id = Random.nextLong()
        name = UUID.randomUUID().toString()
        email = UUID.randomUUID().toString()
    }

    @Test
    fun toDomainCustomer() {
        val createCustomerRequest = CreateCustomerRequest(name, email)

        val customer = underTest.toDomainCustomer(createCustomerRequest)

        assertThat(customer).isEqualTo(Customer(id = null, name = name, email = email))
    }

    @Test
    fun toResponse() {
        val customer = Customer(id = id, name = name, email = email)

        val domainProduct = underTest.toResponse(customer)

        assertThat(domainProduct).isEqualTo(CreateCustomerResponse(id = id, name = name))
    }

    @Test
    fun toGetAllCustomersResponse() {
        val customer = Customer(id = id, name = name, email = email)

        val domainProduct = underTest.toGetAllCustomersResponse(customer)

        assertThat(domainProduct).isEqualTo(GetAllCustomerResponse(id = id, name = name, email = email))
    }

    @Test
    fun toGetCustomerByIdResponse() {
        val customer = Customer(id = id, name = name, email = email)

        val domainProduct = underTest.toGetCustomerByIdResponse(customer)

        assertThat(domainProduct).isEqualTo(GetCustomerByIdResponse(id = id, name = name, email = email))
    }
}