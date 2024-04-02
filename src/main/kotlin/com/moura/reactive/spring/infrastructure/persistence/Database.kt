package com.moura.reactive.spring.infrastructure.persistence

object Database {

    const val NAME = "reactive_spring"

    object Tables {
        const val CUSTOMERS = "customers"
        const val PRODUCTS = "products"
    }

    object Schemas {
        const val REACTIVE_SPRING_SCHEMA = "reactive_spring_schema"
    }
}