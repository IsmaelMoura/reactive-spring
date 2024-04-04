package com.moura.reactive.spring.common.exceptions

class CustomerNotFoundException(customerId: Long) : NotFoundException("Customer with id: [$customerId] does not exist")