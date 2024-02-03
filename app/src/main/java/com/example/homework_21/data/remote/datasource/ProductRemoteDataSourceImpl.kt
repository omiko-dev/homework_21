package com.example.homework_21.data.remote.datasource

import com.example.homework_21.data.common.HandleResource
import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.common.mapper.resourceMapper
import com.example.homework_21.data.mapper.asExternalModel
import com.example.homework_21.data.model.ProductResponse
import com.example.homework_21.data.remote.service.ProductService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService,
    private val handleResource: HandleResource
): ProductRemoteDataSource {
    override suspend fun fetchProducts(): Flow<Resource<List<ProductResponse>>> {
        return handleResource.handleResource {
            productService.getProductList()
        }.map { resource ->
            resource.resourceMapper { productList ->
                productList.map { product ->
                    product.asExternalModel()
                }
            }
        }
    }
}