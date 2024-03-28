package com.moura.reactive.spring.main.config

import com.moura.reactive.spring.application.gateways.ProductGateway
import com.moura.reactive.spring.application.interactors.CreateProductInteractor
import com.moura.reactive.spring.application.interactors.GetAllProductsInteractor
import com.moura.reactive.spring.application.usecases.CreateProductUseCase
import com.moura.reactive.spring.application.usecases.GetAllProductsUseCase
import com.moura.reactive.spring.infrastructure.controllers.dto.product.ProductDTOMapper
import com.moura.reactive.spring.infrastructure.gateways.ProductRepositoryGateway
import com.moura.reactive.spring.infrastructure.gateways.mapper.ProductEntityMapper
import com.moura.reactive.spring.infrastructure.persistence.repository.ProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfig {

    @Bean
    fun createProductUseCase(productGateway: ProductGateway): CreateProductUseCase {
        return CreateProductInteractor(
            productGateway = productGateway
        )
    }

    @Bean
    fun getAllProductsUseCase(productGateway: ProductGateway): GetAllProductsUseCase {
        return GetAllProductsInteractor(
            productGateway = productGateway
        )
    }

    @Bean
    fun productGateway(productRepository: ProductRepository, productEntityMapper: ProductEntityMapper): ProductGateway {
        return ProductRepositoryGateway(
            productRepository = productRepository,
            productEntityMapper = productEntityMapper
        )
    }

    @Bean
    fun productEntityMapper(): ProductEntityMapper {
        return ProductEntityMapper()
    }

    @Bean
    fun productDTOMapper(): ProductDTOMapper {
        return ProductDTOMapper()
    }
}