package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class GetCustomerByIdResponseTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize GetCustomerByIdResponse from JSON`() {
        val path = "src/test/resources/json/get_customer_by_id_response.json"
        val expected = GetCustomerByIdResponse(id = 1L, name = "name", email = "name.lastname@email.com")

        assertThat(deserialize<GetCustomerByIdResponse>(path)).isEqualTo(expected)
    }
}