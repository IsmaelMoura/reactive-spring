package com.moura.reactive.spring.fixture

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object ObjectMapperProvider {

    fun getMapper() = ObjectMapper().registerKotlinModule()
}