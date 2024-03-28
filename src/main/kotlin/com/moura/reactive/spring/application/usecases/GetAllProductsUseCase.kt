package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Product
import kotlinx.coroutines.flow.Flow

fun interface GetAllProductsUseCase {

    fun getAllProducts(): Flow<Product>
}