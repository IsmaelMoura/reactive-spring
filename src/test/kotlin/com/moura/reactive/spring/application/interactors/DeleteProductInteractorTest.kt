package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.common.exceptions.ProductNotFoundException
import com.moura.reactive.spring.fixture.ProductFactory
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random

@UnitTest
@ExtendWith(MockKExtension::class)
class DeleteProductInteractorTest {

    @MockK
    private lateinit var productGateway: ProductGateway

    @InjectMockKs
    private lateinit var underTest: DeleteProductInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should delete product by id successfully`() = runTest {
        val product = ProductFactory.createProduct(id = Random.nextLong())
        givenDeleteByIdCalled(product.id!!)

        underTest.deleteProductById(product.id!!)

        verifyProductGatewayWasCalledOnce(product.id!!)
    }

    @Test
    fun `when product does not exist should throw NotFoundException`() = runTest {
        val product = ProductFactory.createProduct(id = Random.nextLong())
        givenNotFoundExceptionOnGateway(product.id!!)

        assertThrows<ProductNotFoundException> {
            underTest.deleteProductById(product.id!!)
        }

        verifyProductGatewayWasCalledOnce(product.id!!)
    }

    private fun givenDeleteByIdCalled(id: Long) {
        coEvery { productGateway.deleteProductById(id) } just runs
    }

    private fun givenNotFoundExceptionOnGateway(id: Long) {
        coEvery { productGateway.deleteProductById(id) } throws ProductNotFoundException(id)
    }

    private fun verifyProductGatewayWasCalledOnce(id: Long) {
        coVerify { productGateway.deleteProductById(id) }
    }
}