package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.fixture.CustomerFactory
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random

@UnitTest
@ExtendWith(MockKExtension::class)
class GetAllCustomersInteractorTest {

    @MockK
    private lateinit var customerGateway: CustomerGateway

    @InjectMockKs
    private lateinit var underTest: GetAllCustomersInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should get all products successfully`() = runTest {
        val customersToReturn = givenCustomerFlow()
        givenCustomerGatewayReturnAllProduct(customersToReturn)

        val returnedCustomers = underTest.getAllCustomers()

        verifyCustomerGatewayCalledOnce()
        assertThat(returnedCustomers.count()).isNotZero()
        assertThat(customersToReturn).isEqualTo(returnedCustomers)
    }

    @Test
    fun `should return empty list of customer when CustomerGateway return empty list`() = runTest {
        val customersToReturn = givenEmptyCustomerFlow()
        givenCustomerGatewayReturnAllProduct(customersToReturn)

        val returnedCustomers = underTest.getAllCustomers()

        verifyCustomerGatewayCalledOnce()
        assertThat(returnedCustomers.count()).isZero()
        assertThat(customersToReturn).isEqualTo(returnedCustomers)
    }

    private fun givenCustomerFlow() = flow {
        repeat(Random.nextInt(1, 10)) {
            emit(CustomerFactory.createCustomer(id = it.toLong()))
        }
    }

    private fun givenCustomerGatewayReturnAllProduct(customers: Flow<Customer>) {
        coEvery { customerGateway.getAllCustomers() } returns customers
    }

    private fun givenEmptyCustomerFlow() = emptyFlow<Customer>()

    private fun verifyCustomerGatewayCalledOnce() {
        coVerify(exactly = 1) { customerGateway.getAllCustomers() }
    }
}