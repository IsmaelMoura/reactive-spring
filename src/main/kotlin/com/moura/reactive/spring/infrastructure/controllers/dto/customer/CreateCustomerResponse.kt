package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import java.io.Serializable

data class CreateCustomerResponse(
    val name: String,
) : Serializable
