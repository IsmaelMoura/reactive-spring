package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


class GetAllProductsResponseTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize GetAllProductsResponse from JSON`() {
        val path = "src/test/resources/json/get_all_products_response.json"
        val expected = GetAllProductsResponse(id = 1L, name = "name", price = BigDecimal.valueOf(100.0))

        assertThat(deserialize<GetAllProductsResponse>(path)).isEqualTo(expected)
    }
}