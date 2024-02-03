package com.example.homework_21.data.repository

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.common.mapper.resourceMapper
import com.example.homework_21.data.local.datasource.ProductLocalDataSource
import com.example.homework_21.data.mapper.toDomain
import com.example.homework_21.data.remote.datasource.ProductRemoteDataSource
import com.example.homework_21.data.util.NetworkConnectivityUtil
import com.example.homework_21.domain.model.Product
import com.example.homework_21.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val networkConnectivityUtil: NetworkConnectivityUtil,
) : ProductRepository {
    override suspend fun getProduct(): Flow<Resource<List<Product>>> = flow {
        networkConnectivityUtil.networkStatus.collect { it ->
            if (it) {

                productRemoteDataSource.fetchProducts().collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            productLocalDataSource.updateProducts(resource.success.map { it })

                            this.emit(resource.resourceMapper { productList ->
                                productList.map { product ->
                                    product.toDomain()
                                }
                            })
                        }
                        is Resource.Error -> {
                            emit(resource)
                        }
                        is Resource.Loader -> {
                            emit(resource)
                        }
                    }
                }
            } else {
                productLocalDataSource.fetchProducts().collect { productList ->
                    emit(Resource.Success(productList.map { it.toDomain() }))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}