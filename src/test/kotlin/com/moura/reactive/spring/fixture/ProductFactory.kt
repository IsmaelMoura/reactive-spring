package com.moura.reactive.spring.fixture

import com.moura.reactive.spring.domain.entity.Product
import java.math.BigDecimal
import java.util.UUID
import kotlin.random.Random

object ProductFactory {

    fun createProduct(
        id: Long? = null,
        name: String = UUID.randomUUID().toString(),
        price: BigDecimal = BigDecimal.valueOf(Random.nextDouble()),
    ): Product {
        return Product(id, name, price)
    }
}