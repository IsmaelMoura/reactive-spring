package com.moura.reactive.spring.infrastructure.controllers.dto.product

import java.io.Serializable
import java.math.BigDecimal

data class CreateProductRequest(
    val name: String,
    val price: BigDecimal,
) : Serializable
