package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class CreateCustomerRequestTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize CreateCustomerRequest from JSON`() {
        val path = "src/test/resources/json/create_customer_request.json"
        val expected = CreateCustomerRequest(name = "name", email = "name.lastname@email.com")

        assertThat(deserialize<CreateCustomerRequest>(path)).isEqualTo(expected)
    }
}