package com.moura.reactive.spring.infrastructure.controllers.dto.product

import com.moura.reactive.spring.domain.entity.Product

class ProductDTOMapper {

    fun toDomainProduct(createProductRequest: CreateProductRequest): Product {
        return Product(
            name = createProductRequest.name,
            price = createProductRequest.price
        )
    }

    fun toResponse(product: Product): CreateProductResponse {
        return CreateProductResponse(
            id = product.id!!,
            name = product.name,
            price = product.price
        )
    }
}