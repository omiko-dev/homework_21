package com.example.homework_21.data.local.datasource

import com.example.homework_21.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun fetchProducts():  Flow<List<ProductResponse>>
    fun updateProducts(products: List<ProductResponse>)
}