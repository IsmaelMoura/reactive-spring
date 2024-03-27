package com.moura.reactive.spring

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File

@Tag("integration-test")
@Testcontainers
open class BaseIntegrationTest {

    companion object {
        private val dockerComposeContainer: KDockerComposeContainer by lazy { defineDockerCompose() }

        private fun defineDockerCompose() =
            KDockerComposeContainer("docker-compose.yaml")
                .withExposedService(ContainersConstants.POSTGRES_SERVICE_NAME, ContainersConstants.POSTGRES_PORT)
                .withLocalCompose(true)


        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registerPostgresProperties(registry)
        }

        private fun registerPostgresProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url") {
                "r2dbc:postgresql://${
                    dockerComposeContainer.getServiceHost(
                        ContainersConstants.POSTGRES_SERVICE_NAME,
                        ContainersConstants.POSTGRES_PORT
                    )
                }:${
                    dockerComposeContainer.getServicePort(
                        ContainersConstants.POSTGRES_SERVICE_NAME,
                        ContainersConstants.POSTGRES_PORT
                    )
                }/reactive_spring?schema=reactive_spring_schema"
            }
            registry.add("spring.r2dbc.username") { ContainersConstants.POSTGRES_USERNAME }
            registry.add("spring.r2dbc.password") { ContainersConstants.POSTGRES_PASSWORD }
        }

        @JvmStatic
        @BeforeAll
        fun setUp() {
            dockerComposeContainer.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            dockerComposeContainer.stop()
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