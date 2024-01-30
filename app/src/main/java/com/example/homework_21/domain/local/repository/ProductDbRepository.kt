package com.example.homework_21.domain.local.repository

import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductDbRepository {
    fun getAll():  Flow<Resource<List<ProductModel>>>

    fun insertAll(vararg product: ProductModel)

    fun delete(product: ProductModel)
}