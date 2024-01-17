package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Product

interface CreateProductUseCase {

    suspend fun createProduct(product: Product): Product
}