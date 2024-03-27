package com.moura.reactive.spring.infrastructure.persistence.repository

import com.moura.reactive.spring.infrastructure.persistence.entity.ProductEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductRepository : CoroutineCrudRepository<ProductEntity, Long>