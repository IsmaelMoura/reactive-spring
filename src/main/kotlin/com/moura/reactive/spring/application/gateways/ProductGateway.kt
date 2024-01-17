package com.moura.reactive.spring.application.gateways

import com.moura.reactive.spring.domain.entity.Product

interface ProductGateway {
    suspend fun createProduct(product: Product): Product
}