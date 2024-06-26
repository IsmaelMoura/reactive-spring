package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.domain.entity.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import kotlin.random.Random


class ProductDTOMapperTest {

    private val underTest = ProductDTOMapper()

    @Test
    fun toDomainProduct() {
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val createProductRequest = CreateProductRequest(name, price)

        val domainProduct = underTest.toDomainProduct(createProductRequest)

        assertThat(domainProduct).isEqualTo(Product(id = null, name = name, price = price))
    }

    @Test
    fun toCreateProductResponse() {
        val id = Random.nextLong()
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val product = Product(id = id, name = name, price = price)

        val response = underTest.toCreateProductResponse(product)

        assertThat(response).isEqualTo(CreateProductResponse(id = id, name = name, price = price))
    }

    @Test
    fun toGetAllProductsResponse() {
        val id = Random.nextLong()
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val product = Product(id = id, name = name, price = price)

        val response = underTest.toGetAllProductsResponse(product)

        assertThat(response).isEqualTo(GetAllProductsResponse(id = id, name = name, price = price))
    }
}