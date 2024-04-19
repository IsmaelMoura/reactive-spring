package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.fixture.DeserializationTesterModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal


class CreateProductRequestTest : DeserializationTesterModule() {

    @Test
    fun `should deserialize CreateProductRequest from JSON`() {
        val path = "src/test/resources/json/create_product_request.json"
        val expected = CreateProductRequest("product", BigDecimal.valueOf(100.0))

        assertThat(deserialize<CreateProductRequest>(path)).isEqualTo(expected)
    }
}