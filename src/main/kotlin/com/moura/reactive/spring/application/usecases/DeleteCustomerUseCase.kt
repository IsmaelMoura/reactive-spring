package com.moura.reactive.spring.application.usecases

fun interface DeleteCustomerUseCase {

    suspend fun deleteCustomerById(id: Long)
}