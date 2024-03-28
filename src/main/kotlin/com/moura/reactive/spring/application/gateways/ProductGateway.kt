package com.moura.reactive.spring.application.gateways

import com.moura.reactive.spring.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductGateway {

    suspend fun createProduct(product: Product): Product

    fun getAllProducts(): Flow<Product>
}