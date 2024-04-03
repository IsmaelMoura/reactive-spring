package com.moura.reactive.spring.application.usecases

import com.moura.reactive.spring.common.alias.UpdatedProduct
import java.math.BigDecimal

interface UpdateProductPriceByIdUseCase {

    suspend fun updateProductPriceById(productId: Long, newPrice: BigDecimal): UpdatedProduct
}