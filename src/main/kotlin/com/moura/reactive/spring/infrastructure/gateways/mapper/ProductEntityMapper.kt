package com.moura.reactive.spring.infrastructure.gateways.mapper

import com.moura.reactive.spring.common.mapper.EntityMapper
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.infrastructure.persistence.entity.ProductEntity

class ProductEntityMapper : EntityMapper<Product, ProductEntity> {

    override fun toEntity(domain: Product): ProductEntity {
        return ProductEntity(
            id = domain.id,
            name = domain.name,
            price = domain.price
        )
    }

    override fun toDomainObject(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = entity.price
        )
    }
}