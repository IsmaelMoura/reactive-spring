package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.fixture.CustomerFactory
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
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random


@ExtendWith(MockKExtension::class)
class DeleteCustomerInteractorTest {

    @MockK
    private lateinit var customerGateway: CustomerGateway

    @InjectMockKs
    private lateinit var underTest: DeleteCustomerInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should delete customer by id successfully`() = runTest {
        val customer = CustomerFactory.createCustomer(id = Random.nextLong())
        givenDeleteByIdCalled(customer.id!!)

        underTest.deleteCustomerById(customer.id!!)

        verifyCustomerGatewayWasCalledOnce(customer.id!!)
    }

    private fun givenDeleteByIdCalled(id: Long) {
        coEvery { customerGateway.deleteCustomerById(id) } just runs
    }

    private fun verifyCustomerGatewayWasCalledOnce(id: Long) {
        coVerify { customerGateway.deleteCustomerById(id) }
    }
}