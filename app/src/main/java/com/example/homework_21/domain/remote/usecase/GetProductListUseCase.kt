package com.example.homework_21.domain.remote.usecase

import com.example.homework_21.data.common.mapper.resourceMapper
import com.example.homework_21.domain.remote.repository.ProductRepository
import com.example.homework_21.presentation.mapper.toPresenter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = productRepository.getProductList()
        .map {
            it.resourceMapper { productList ->
                productList.map { product ->
                    product.toPresenter()
                }
            }
        }
}