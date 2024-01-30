package com.example.homework_21.domain.local.usecase

import com.example.homework_21.domain.local.repository.ProductDbRepository
import com.example.homework_21.presentation.mapper.toDomain
import com.example.homework_21.presentation.model.ProductUI
import javax.inject.Inject

class DeleteProductDbUseCase @Inject constructor(
    private val productDbRepository: ProductDbRepository
) {
    operator fun invoke(productUI: ProductUI) =
        productDbRepository.delete(product = productUI.toDomain())
}