package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.application.usecases.GetAllCustomersUseCase
import com.moura.reactive.spring.domain.entity.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import mu.KotlinLogging

class GetAllCustomersInteractor(
    private val customerGateway: CustomerGateway,
) : GetAllCustomersUseCase {

    override fun getAllCustomers(): Flow<Customer> {
        return customerGateway.getAllCustomers()
            .catch { throwable ->
                logger.error(throwable) { "Error while getting all customers from database" }
            }
            .onCompletion { throwable ->
                throwable ?: logger.info { "Found all customers from database" }
            }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}