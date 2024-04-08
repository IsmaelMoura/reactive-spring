package com.moura.reactive.spring.infrastructure.controllers.dto.customer

import java.io.Serializable

data class GetCustomerByIdResponse(
    val id: Long,
    val name: String,
    val email: String,
) : Serializable