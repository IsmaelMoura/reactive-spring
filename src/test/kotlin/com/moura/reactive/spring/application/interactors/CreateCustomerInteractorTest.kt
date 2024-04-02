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
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.random.Random.Default.nextLong

@UnitTest
@ExtendWith(MockKExtension::class)
class CreateCustomerInteractorTest {

    @MockK
    private lateinit var customerGateway: CustomerGateway

    @InjectMockKs
    private lateinit var underTest: CreateCustomerInteractor

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should create customer successfully`() = runTest {
        // given
        val customer = CustomerFactory.createCustomer()
        givenSuccessCallToCustomerGateway(customer)

        // when
        val createdCustomer = underTest.createCustomer(customer)

        // then
        verifyCustomerGatewayCalled(customer)
        assertThat(createdCustomer.id).isNotNull
        assertThat(customer.name).isEqualTo(createdCustomer.name)
        assertThat(customer.email).isEqualTo(createdCustomer.email)
    }

    private fun givenSuccessCallToCustomerGateway(customer: Customer) {
        coEvery { customerGateway.createCustomer(any()) } returns customer.copy(id = nextLong())
    }

    private fun verifyCustomerGatewayCalled(customer: Customer) {
        coVerify(exactly = 1) { customerGateway.createCustomer(customer) }
    }
}