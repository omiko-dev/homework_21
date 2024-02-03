package com.example.homework_21.data.remote.datasource

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRemoteDataSource {
    suspend fun fetchProducts(): Flow<Resource<List<ProductResponse>>>
}