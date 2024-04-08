package com.moura.reactive.spring.infrastructure.controllers

import com.moura.reactive.spring.application.usecases.CreateProductUseCase
import com.moura.reactive.spring.application.usecases.DeleteProductUseCase
import com.moura.reactive.spring.application.usecases.GetAllProductsUseCase
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.controllers.dto.product.CreateProductRequest
import com.moura.reactive.spring.infrastructure.controllers.dto.product.CreateProductResponse
import com.moura.reactive.spring.infrastructure.controllers.dto.product.GetAllProductsResponse
import com.moura.reactive.spring.infrastructure.controllers.dto.product.ProductDTOMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Endpoints.PRODUCTS_ENDPOINT)
class ProductController(
    private val productDTOMapper: ProductDTOMapper,
    private val createProductUseCase: CreateProductUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
) {

    @PostMapping
    suspend fun createProduct(
        @RequestBody createProductRequest: CreateProductRequest,
    ): ResponseEntity<CreateProductResponse> {
        logger.info { "Received POST request to create product. createProductRequest: [$createProductRequest]" }

        return productDTOMapper.toDomainProduct(createProductRequest)
            .let { product -> createProductUseCase.createProduct(product) }
            .let { product -> productDTOMapper.toCreateProductResponse(product) }
            .let { response -> ResponseEntity.ok(response) }
    }

    @GetMapping
    fun getAllProducts(): Flow<GetAllProductsResponse> {
        logger.info { "Received GET request for all products." }

        return getAllProductsUseCase.getAllProducts()
            .map { productDTOMapper.toGetAllProductsResponse(it) }
    }

    @DeleteMapping("/{productId}")
    suspend fun deleteProductById(@PathVariable productId: Long): ResponseEntity<Any> {
        logger.info { "Received DELETE request for product. productId: [$productId]" }

        return deleteProductUseCase.deleteProductById(productId)
            .let { ResponseEntity.noContent().build() }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}