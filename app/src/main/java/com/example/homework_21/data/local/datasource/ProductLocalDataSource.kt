package com.example.homework_21.data.local.datasource

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun fetchProducts():  Flow<List<Product>>
    fun updateProducts(products: List<ProductEntity>)
}