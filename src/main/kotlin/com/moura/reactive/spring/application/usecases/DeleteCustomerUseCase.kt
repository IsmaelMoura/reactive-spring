package com.moura.reactive.spring.application.usecases

interface DeleteCustomerUseCase {

    suspend fun deleteCustomerById(id: Long)
}