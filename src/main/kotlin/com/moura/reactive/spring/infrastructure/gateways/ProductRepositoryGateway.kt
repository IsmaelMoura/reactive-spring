package com.moura.reactive.spring.infrastructure.gateways

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.gateways.mapper.ProductEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.withContext
import mu.KotlinLogging

class ProductRepositoryGateway(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper,
    private val coroutineDispatcher: CoroutineDispatcher
) : ProductGateway {

    override suspend fun createProduct(product: Product): Product {
        return runCatching {
            withContext(coroutineDispatcher) {
                productEntityMapper.toEntity(product)
                    .let { productRepository.save(it) }
                    .let { productEntityMapper.toDomainObject(it) }
            }
        }.onFailure { throwable ->
            logger.error(throwable) { "Unable to save the product: [$product] on database." }
        }.onSuccess { persistedProduct ->
            logger.info { "Successfully persisted the product: [${productEntityMapper.toEntity(persistedProduct).id}] on database" }
        }.getOrThrow()
    }

    override fun getAllProducts(): Flow<Product> {
        return productRepository.findAll()
            .flowOn(Dispatchers.IO)
            .catch { throwable ->
                logger.error(throwable) { "Error while getting all products from database" }
            }
            .onCompletion { throwable ->
                throwable ?: logger.info { "Found all products from database." }
            }
            .map(productEntityMapper::toDomainObject)
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}