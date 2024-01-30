package com.example.homework_21.domain.local.usecase

import com.example.homework_21.domain.local.repository.ProductDbRepository
import com.example.homework_21.presentation.mapper.toDomain
import com.example.homework_21.presentation.model.ProductUI
import javax.inject.Inject

class InsertProductListDbUseCase @Inject constructor(
    private val productDbRepository: ProductDbRepository
) {
    operator fun invoke(productUIList: List<ProductUI>) {
        val productModels = productUIList.map { it.toDomain() }.toTypedArray()
        productDbRepository.insertAll(*productModels)
    }
}