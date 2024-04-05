package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Product

fun interface GetProductByIdUseCase {

    suspend fun getProductById(productId: Long): Product
}