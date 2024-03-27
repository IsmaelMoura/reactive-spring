package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.application.usecases.CreateProductUseCase
import com.moura.reactive.spring.domain.entity.Product

class CreateProductInteractor(
    private val productGateway: ProductGateway,
) : CreateProductUseCase {

    override suspend fun createProduct(product: Product): Product {
        return productGateway.createProduct(product)
    }
}