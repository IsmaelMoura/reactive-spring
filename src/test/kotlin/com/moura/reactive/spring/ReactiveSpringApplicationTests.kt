package com.moura.reactive.spring

import com.moura.reactive.spring.infrastructure.persistence.entity.ProductEntity
import com.moura.reactive.spring.infrastructure.persistence.repository.ProductRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import java.math.BigDecimal

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ReactiveSpringApplicationTests: BaseIntegrationTest() {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun contextLoads() = runTest {
        productRepository.save(ProductEntity(name = "name", price = BigDecimal.ONE))
        assertThat(productRepository.findAll().count())
            .isEqualTo(1)
    }
}
