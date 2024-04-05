package com.moura.reactive.spring.application.interactors

import com.moura.reactive.spring.UnitTest
import com.moura.reactive.spring.application.gateways.CustomerGateway
import com.moura.reactive.spring.domain.entity.Customer
import com.moura.reactive.spring.fixture.CustomerFactory
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
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
        assertThat(customersToReturn.toList()).isEqualTo(returnedCustomers.toList())
    }

    @Test
    fun `should return empty list of customer when CustomerGateway return empty list`() = runTest {
        val customersToReturn = givenEmptyCustomerFlow()
        givenCustomerGatewayReturnAllProduct(customersToReturn)

        val returnedCustomers = underTest.getAllCustomers()

        verifyCustomerGatewayCalledOnce()
        assertThat(returnedCustomers.count()).isZero()
    }

    private fun givenCustomerFlow() =
        buildList {
            repeat(Random.nextInt(1, 10)) {
                add(CustomerFactory.createCustomer(id = it.toLong()))
            }
        }.asFlow()

    private fun givenCustomerGatewayReturnAllProduct(customers: Flow<Customer>) {
        every { customerGateway.getAllCustomers() } returns customers
    }

    private fun givenEmptyCustomerFlow() = emptyFlow<Customer>()

    private fun verifyCustomerGatewayCalledOnce() {
        verify(exactly = 1) { customerGateway.getAllCustomers() }
    }
}