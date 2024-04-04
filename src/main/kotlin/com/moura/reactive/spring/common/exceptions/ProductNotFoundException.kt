package com.moura.reactive.spring.common.exceptions

class ProductNotFoundException(val productId: Long) :
    NotFoundException("Product with id: [$productId] was not found")