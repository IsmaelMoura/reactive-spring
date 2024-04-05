package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.aliases.UpdatedProduct
import java.math.BigDecimal

fun interface UpdateProductPriceByIdUseCase {

    suspend fun updateProductPriceById(productId: Long, newPrice: BigDecimal): UpdatedProduct
}