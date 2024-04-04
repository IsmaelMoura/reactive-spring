package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.application.usecases.DeleteProductUseCase

class DeleteProductInteractor(
    private val productGateway: ProductGateway,
) : DeleteProductUseCase {

    override suspend fun deleteProductById(productId: Long) {
        productGateway.deleteProductById(productId)
    }
}