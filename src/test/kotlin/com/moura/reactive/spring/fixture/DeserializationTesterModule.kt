package com.moura.reactive.spring.fixture

import java.io.File

open class DeserializationTesterModule {

    protected val objectMapper = ObjectMapperProvider.getMapper()

    protected inline fun <reified T> deserialize(path: String): T {
        return objectMapper.readValue(File(path), T::class.java)
    }
}