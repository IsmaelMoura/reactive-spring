package com.moura.reactive.spring.fixture

import com.moura.reactive.spring.domain.entity.Customer
import java.util.*

object CustomerFactory {

    fun createCustomer(
        id: Long? = null,
        name: String = UUID.randomUUID().toString(),
        email: String = UUID.randomUUID().toString()
    ): Customer {
        return Customer(id, name, email)
    }
}