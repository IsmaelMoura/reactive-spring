package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CreateProductResponseTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize CreateProductResponse from JSON`() {
        val path = "src/test/resources/json/create_product_response.json"
        val expected = CreateProductResponse(id = 10L, name = "product", price = BigDecimal.valueOf(100.0))

        assertThat(deserialize<CreateProductResponse>(path)).isEqualTo(expected)
    }
}