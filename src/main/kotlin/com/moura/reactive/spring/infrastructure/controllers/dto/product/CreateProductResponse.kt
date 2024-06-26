package com.moura.reactive.spring.infrastructure.controllers.dto.product

import java.io.Serializable
import java.math.BigDecimal

data class CreateProductResponse(
    val id: Long,
    val name: String,
    val price: BigDecimal,
) : Serializable
