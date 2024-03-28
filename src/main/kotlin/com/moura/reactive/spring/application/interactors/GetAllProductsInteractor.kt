package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.application.usecases.GetAllProductsUseCase
import com.moura.reactive.spring.domain.entity.Product
import kotlinx.coroutines.flow.Flow

class GetAllProductsInteractor(
    private val productGateway: ProductGateway,
) : GetAllProductsUseCase {

    override fun getAllProducts(): Flow<Product> {
        return productGateway.getAllProducts()
    }
}