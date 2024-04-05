package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedProduct

fun interface UpdateProductNameByIdUseCase {

    suspend fun updateProductNameById(productId: Long, name: String): UpdatedProduct
}