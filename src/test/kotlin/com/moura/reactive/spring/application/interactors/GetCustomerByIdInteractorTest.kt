package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.common.exceptions.CustomerNotFoundException
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.fixture.CustomerFactory
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
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random

@UnitTest
@ExtendWith(MockKExtension::class)
class GetCustomerByIdInteractorTest {

    @MockK
    private lateinit var customerGateway: CustomerGateway

    @InjectMockKs
    private lateinit var underTest: GetCustomerByIdInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should get customer by id successfully`() = runTest {
        val customerId = Random.nextLong()
        val customerToReturn = CustomerFactory.createCustomer(id = customerId)

        givenGatewayReturnCustomerById(customerToReturn)

        val returnedCustomer = underTest.getCustomerById(customerId)

        verifyCustomerGatewayCalledOnce(customerId)
        assertThat(customerToReturn).isEqualTo(returnedCustomer)
    }

    @Test
    fun `should throw CustomerNotFoundException when customer does not exist`() = runTest {
        val customerId = Random.nextLong()

        givenNotFoundExceptionOnGateway(customerId)
        assertCustomerNotFoundExceptionThrown(customerId)
        verifyCustomerGatewayCalledOnce(customerId)
    }

    private fun givenNotFoundExceptionOnGateway(customerId: Long) {
        coEvery { customerGateway.getCustomerById(customerId) } throws CustomerNotFoundException(customerId)
    }

    private fun givenGatewayReturnCustomerById(customer: Customer) {
        coEvery { customerGateway.getCustomerById(any()) } returns customer
    }

    private suspend fun assertCustomerNotFoundExceptionThrown(customerId: Long) {
        assertThrows<CustomerNotFoundException> { underTest.getCustomerById(customerId) }
    }

    private fun verifyCustomerGatewayCalledOnce(customerId: Long) {
        coVerify(exactly = 1) { customerGateway.getCustomerById(customerId) }
    }
}