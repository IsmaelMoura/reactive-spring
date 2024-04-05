package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedProduct
import com.moura.reactive.spring.domain.entity.Product

fun interface UpdateProductByIdUseCase {

    suspend fun updateProductById(productId: Long, product: Product): UpdatedProduct
}