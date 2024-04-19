package com.moura.reactive.spring.fixture

import java.io.File
import java.io.Serializable

open class DeserializationTesterModule {

    protected val objectMapper = ObjectMapperProvider.getMapper()

    protected inline fun <reified T : Serializable> deserialize(path: String): T {
        return objectMapper.readValue(File(path), T::class.java)
    }
}