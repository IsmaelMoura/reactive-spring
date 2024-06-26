package com.moura.reactive.spring

import com.moura.reactive.spring.infrastructure.persistence.Database
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.DockerComposeContainer
import java.io.File

@IntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTest {

    companion object {
        private val docker: KDockerComposeContainer by lazy { defineDockerCompose() }

        private fun defineDockerCompose() =
            KDockerComposeContainer("docker-compose.yaml")
                .withExposedService(ContainersConstants.POSTGRES_SERVICE_NAME, ContainersConstants.POSTGRES_PORT)


        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registerPostgresProperties(registry)
        }

        private fun registerPostgresProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url") {
                "r2dbc:postgresql://${
                    docker.getServiceHost(
                        ContainersConstants.POSTGRES_SERVICE_NAME,
                        ContainersConstants.POSTGRES_PORT
                    )
                }:${
                    docker.getServicePort(
                        ContainersConstants.POSTGRES_SERVICE_NAME,
                        ContainersConstants.POSTGRES_PORT
                    )
                }/${Database.NAME}?schema=${Database.Schemas.REACTIVE_SPRING_SCHEMA}"
            }
            registry.add("spring.r2dbc.username") { ContainersConstants.POSTGRES_USERNAME }
            registry.add("spring.r2dbc.password") { ContainersConstants.POSTGRES_PASSWORD }
        }

        @JvmStatic
        @BeforeAll
        fun setUp() {
            docker.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            docker.stop()
        }
    }

    class KDockerComposeContainer(filePath: String) : DockerComposeContainer<KDockerComposeContainer>(File(filePath))

    private object ContainersConstants {
        const val POSTGRES_SERVICE_NAME = "postgres"
        const val POSTGRES_PORT = 5432
        const val POSTGRES_USERNAME = "root"
        const val POSTGRES_PASSWORD = "root"
    }
}