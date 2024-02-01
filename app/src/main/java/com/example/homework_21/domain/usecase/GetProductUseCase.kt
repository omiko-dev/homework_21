package com.example.homework_21.domain.usecase

import com.example.homework_21.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = productRepository.getProduct()
}