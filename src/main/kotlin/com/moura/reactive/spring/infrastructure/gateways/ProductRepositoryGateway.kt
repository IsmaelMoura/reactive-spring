package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.gateways.mapper.ProductEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryGateway(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper,
) : ProductGateway {

    override suspend fun createProduct(product: Product): Product {
        return productEntityMapper.toEntity(product)
            .let { productRepository.save(it) }
            .let(productEntityMapper::toDomainObject)
    }

    override fun getAllProducts(): Flow<Product> {
        return productRepository.findAll()
            .map(productEntityMapper::toDomainObject)
    }

    override suspend fun deleteProductById(productId: Long) {
        productRepository.deleteById(productId)
    }
}