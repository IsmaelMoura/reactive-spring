package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class CreateCustomerResponseTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize CreateCustomerResponse from JSON`() {
        val path = "src/test/resources/json/create_customer_response.json"
        val expected = CreateCustomerResponse(id = 10L, name = "name")

        assertThat(deserialize<CreateCustomerResponse>(path)).isEqualTo(expected)
    }
}