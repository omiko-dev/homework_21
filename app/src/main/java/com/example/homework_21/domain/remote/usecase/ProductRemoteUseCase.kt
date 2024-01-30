package com.example.homework_21.domain.remote.usecase

import javax.inject.Inject

data class ProductRemoteUseCase @Inject constructor(
    val getProductListUseCase: GetProductListUseCase
)