package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Product

fun interface CreateProductUseCase {

    suspend fun createProduct(product: Product): Product
}