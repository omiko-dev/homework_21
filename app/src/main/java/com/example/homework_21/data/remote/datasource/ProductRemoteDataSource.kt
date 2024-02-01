package com.example.homework_21.data.remote.datasource

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRemoteDataSource {
    suspend fun fetchProducts(): Flow<Resource<List<Product>>>
}