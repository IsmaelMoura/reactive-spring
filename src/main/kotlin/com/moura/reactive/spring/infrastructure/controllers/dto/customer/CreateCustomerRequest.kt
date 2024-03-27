package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import java.io.Serializable

data class CreateCustomerRequest(
    val name: String,
    val email: String,
) : Serializable
