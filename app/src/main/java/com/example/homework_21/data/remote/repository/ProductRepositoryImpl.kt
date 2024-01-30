package com.example.homework_21.data.remote.repository

import com.example.homework_21.data.common.HandleResource
import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.common.mapper.resourceMapper
import com.example.homework_21.data.remote.mapper.toDomain
import com.example.homework_21.data.remote.service.ProductService
import com.example.homework_21.domain.model.ProductModel
import com.example.homework_21.domain.remote.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val handleResource: HandleResource
) : ProductRepository {
    override suspend fun getProductList(): Flow<Resource<List<ProductModel>>> =
        handleResource.handleResource {
            productService.getProductList()
        }.map {
            it.resourceMapper { productList ->
                productList.map { product ->
                    product.toDomain()
                }
            }
        }
}