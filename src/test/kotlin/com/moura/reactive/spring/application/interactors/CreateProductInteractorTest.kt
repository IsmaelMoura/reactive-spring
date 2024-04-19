package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.domain.entity.Product
import com.moura.reactive.spring.fixture.ProductFactory
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random


@ExtendWith(MockKExtension::class)
class CreateProductInteractorTest {

    @MockK
    private lateinit var productGateway: ProductGateway

    @InjectMockKs
    private lateinit var underTest: CreateProductInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should create product successfully`() = runTest {
        val product = ProductFactory.createProduct()
        givenSuccessfulCallToProductGateway(product)

        val createdProduct = underTest.createProduct(product)

        verifyProductGatewayCalledOnce(product)
        assertThat(createdProduct.id).isNotNull
        assertThat(createdProduct.name).isEqualTo(product.name)
        assertThat(createdProduct.price).isEqualTo(product.price)
    }

    private fun givenSuccessfulCallToProductGateway(product: Product) {
        coEvery { productGateway.createProduct(any()) } returns product.copy(id = Random.nextLong())
    }

    private fun verifyProductGatewayCalledOnce(product: Product) {
        coVerify(exactly = 1) { productGateway.createProduct(product) }
    }
}