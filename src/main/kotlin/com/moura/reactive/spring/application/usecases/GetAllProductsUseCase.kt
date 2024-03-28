package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {

    fun getAllProducts(): Flow<Product>
}