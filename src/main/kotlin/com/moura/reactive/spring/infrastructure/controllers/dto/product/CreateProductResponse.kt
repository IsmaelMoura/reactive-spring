package com.moura.reactive.spring.infrastructure.controllers.dto.product

import java.math.BigDecimal

data class CreateProductResponse(
    val name: String,
    val price: BigDecimal
)
