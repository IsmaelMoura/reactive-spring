package com.moura.reactive.spring.application.usecases

fun interface DeleteProductUseCase {

    suspend fun deleteProductById(productId: Long)
}