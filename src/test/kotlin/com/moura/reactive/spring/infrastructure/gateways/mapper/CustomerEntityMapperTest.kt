package com.moura.reactive.spring.infrastructure.gateways.mapper

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.persistence.entity.CustomerEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.random.Random

@UnitTest
class CustomerEntityMapperTest {

    private val customerEntityMapper = CustomerEntityMapper()

    @Test
    fun toEntity() {
        val name = "customer-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val customer = Customer(name, email)

        val customerEntity = customerEntityMapper.toEntity(customer)

        assertThat(customerEntity).isEqualTo(CustomerEntity(id = null, name, email))
    }

    @Test
    fun toDomainObject() {
        val id = Random.nextLong()
        val name = "customer-${UUID.randomUUID()}"
        val email = "email@${UUID.randomUUID()}.com"
        val customer = CustomerEntity(id, name, email)

        val customerEntity = customerEntityMapper.toDomainObject(customer)

        assertThat(customerEntity).isEqualTo(Customer(name, email))
    }
}