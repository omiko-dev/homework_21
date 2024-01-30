package com.example.homework_21.domain.remote.repository

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(): Flow<Resource<List<ProductModel>>>
}