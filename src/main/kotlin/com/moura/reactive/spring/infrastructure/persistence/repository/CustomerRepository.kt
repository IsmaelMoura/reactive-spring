package com.moura.reactive.spring.infrastructure.persistence.repository

import com.moura.reactive.spring.infrastructure.persistence.entity.CustomerEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository : CoroutineCrudRepository<CustomerEntity, Long>