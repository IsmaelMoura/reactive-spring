package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductRepositoryGatewayTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var underTest: ProductRepositoryGateway

    @Test
    fun createProduct() {
        TODO()
    }

    @Test
    fun getAllProducts() {
        TODO()
    }

    @Test
    fun deleteProductById() {
        TODO()
    }
}