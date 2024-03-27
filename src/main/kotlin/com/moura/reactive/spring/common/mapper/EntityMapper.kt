package com.moura.reactive.spring.common.mapper

interface EntityMapper<DOMAIN, ENTITY> {

    fun toEntity(domain: DOMAIN): ENTITY

    fun toDomainObject(entity: ENTITY): DOMAIN
}