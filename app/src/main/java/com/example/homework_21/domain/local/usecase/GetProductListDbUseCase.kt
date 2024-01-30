package com.example.homework_21.domain.local.usecase

import com.example.homework_21.data.common.mapper.resourceMapper
import com.example.homework_21.domain.local.repository.ProductDbRepository
import com.example.homework_21.presentation.mapper.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductListDbUseCase @Inject constructor(
    private val productDbRepository: ProductDbRepository
) {
    operator fun invoke() = productDbRepository.getAll()
        .map {
            it.resourceMapper { productList ->
                productList.map { product ->
                    product.toPresenter()
                }
            }
        }
}