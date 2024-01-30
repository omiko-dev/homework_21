package com.example.homework_21.domain.local.usecase

import javax.inject.Inject

data class ProductLocalUseCase @Inject constructor(
    val deleteProductDbUseCase: DeleteProductDbUseCase,
    val getProductListDbUseCase: GetProductListDbUseCase,
    val insertProductListDbUseCase: InsertProductListDbUseCase
)