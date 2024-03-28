package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.domain.entity.Product
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import kotlin.random.Random

class ProductDTOMapperTest {

    private val productDTOMapper = ProductDTOMapper()

    @Test
    fun toDomainProduct() {
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val createProductRequest = CreateProductRequest(name, price)

        val domainProduct = productDTOMapper.toDomainProduct(createProductRequest)

        assertThat(domainProduct).isEqualTo(Product(name, price))
    }

    @Test
    fun toResponse() {
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val product = Product(name, price)

        val response = productDTOMapper.toResponse(product)

        assertThat(response).isEqualTo(CreateProductResponse(name, price))
    }
}