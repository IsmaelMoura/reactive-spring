package com.moura.reactive.spring.application.usecases

interface DeleteProductUseCase {

    suspend fun deleteProductById(productId: Long)
}