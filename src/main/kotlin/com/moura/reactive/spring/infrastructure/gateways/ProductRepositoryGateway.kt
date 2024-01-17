package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.gateways.mapper.ProductEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryGateway(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper
) : ProductGateway {

    override suspend fun createProduct(product: Product): Product {
        return withContext(Dispatchers.IO) {
            productEntityMapper.toEntity(product)
                .let { productRepository.save(it) }
                .let { productEntityMapper.toDomainObject(it) }
        }
    }
}