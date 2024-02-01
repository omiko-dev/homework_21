package com.example.homework_21.data.local.datasource

import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.data.mapper.asExternalModel
import com.example.homework_21.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(
    private val dao: ProductDao
) : ProductLocalDataSource {
    override suspend fun fetchProducts(): Flow<List<Product>> {
        return dao.getAll().map { productList ->
            productList.map { product ->
                product.asExternalModel()
            }
        }
    }

    override fun updateProducts(products: List<ProductEntity>) {
        dao.insertAll(*products.toTypedArray())
    }
}