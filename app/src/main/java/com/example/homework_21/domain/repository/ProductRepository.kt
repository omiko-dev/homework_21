package com.example.homework_21.domain.repository

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProduct(): Flow<Resource<List<Product>>>
}