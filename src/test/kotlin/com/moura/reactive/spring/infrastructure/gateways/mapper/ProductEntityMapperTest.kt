package com.moura.reactive.spring.infrastructure.gateways.mapper

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.persistence.entity.ProductEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import kotlin.random.Random

@UnitTest
class ProductEntityMapperTest {

    private val productEntityMapper = ProductEntityMapper()

    @Test
    fun toEntity() {
        val id = Random.nextLong()
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val product = Product(id = id, name = name, price = price)

        val productEntity = productEntityMapper.toEntity(product)

        assertThat(productEntity).isEqualTo(ProductEntity(id = id, name = name, price = price))
    }

    @Test
    fun toDomainObject() {
        val id = Random.nextLong()
        val name = "product-${UUID.randomUUID()}"
        val price = BigDecimal.valueOf(Random.nextLong())
        val productEntity = ProductEntity(id, name, price)

        val domainProduct = productEntityMapper.toDomainObject(productEntity)

        assertThat(domainProduct).isEqualTo(Product(id = id, name = name, price = price))
    }
}