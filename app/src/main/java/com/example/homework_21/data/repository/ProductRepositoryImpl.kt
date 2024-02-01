package com.example.homework_21.data.repository

import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.local.datasource.ProductLocalDataSource
import com.example.homework_21.data.mapper.asEntity
import com.example.homework_21.data.remote.datasource.ProductRemoteDataSource
import com.example.homework_21.data.util.NetworkConnectivityUtil
import com.example.homework_21.domain.model.Product
import com.example.homework_21.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val networkConnectivityUtil: NetworkConnectivityUtil
) : ProductRepository {
    override suspend fun getProduct(): Flow<Resource<List<Product>>> = flow {
        networkConnectivityUtil.networkStatus.collect { it ->
            if (it) {
                productRemoteDataSource.fetchProducts().collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            productLocalDataSource.updateProducts(resource.success.map { it.asEntity() })
                            this.emit(resource)
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
                productLocalDataSource.fetchProducts().collect {
                    emit(Resource.Success(it))
                }
            }
        }
    }
}