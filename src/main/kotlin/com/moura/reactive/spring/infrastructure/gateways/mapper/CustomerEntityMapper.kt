package com.moura.reactive.spring.infrastructure.gateways.mapper

import com.moura.reactive.spring.common.mapper.EntityMapper
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.infrastructure.persistence.entity.CustomerEntity

class CustomerEntityMapper : EntityMapper<Customer, CustomerEntity> {

    override fun toEntity(domain: Customer): CustomerEntity {
        return CustomerEntity(
            id = domain.id,
            name = domain.name,
            email = domain.email
        )
    }

    override fun toDomainObject(entity: CustomerEntity): Customer {
        return Customer(
            id = entity.id,
            name = entity.name,
            email = entity.email
        )
    }
}