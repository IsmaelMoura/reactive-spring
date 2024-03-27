package com.moura.reactive.spring.infrastructure.controllers

import com.moura.reactive.spring.application.usecases.CreateProductUseCase
import com.moura.reactive.spring.infrastructure.controllers.dto.product.CreateProductRequest
import com.moura.reactive.spring.infrastructure.controllers.dto.product.CreateProductResponse
import com.moura.reactive.spring.infrastructure.controllers.dto.product.ProductDTOMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Endpoints.PRODUCTS_ENDPOINT)
class ProductController(
    private val createProductUseCase: CreateProductUseCase,
    private val productDTOMapper: ProductDTOMapper,
) {

    @PostMapping
    suspend fun createProduct(
        @RequestBody createProductRequest: CreateProductRequest,
    ): ResponseEntity<CreateProductResponse> {
        return productDTOMapper.toDomainProduct(createProductRequest)
            .let { product -> createProductUseCase.createProduct(product) }
            .let { product -> productDTOMapper.toResponse(product) }
            .let { response -> ResponseEntity.ok(response) }
    }
}