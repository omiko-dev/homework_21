package com.example.homework_21.di

import com.example.homework_21.data.common.HandleResource
import com.example.homework_21.data.local.datasource.ProductLocalDataSource
import com.example.homework_21.data.remote.datasource.ProductRemoteDataSource
import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.remote.service.ProductService
import com.example.homework_21.data.local.datasource.ProductLocalDataSourceImpl
import com.example.homework_21.data.remote.datasource.ProductRemoteDataSourceImpl
import com.example.homework_21.data.repository.ProductRepositoryImpl
import com.example.homework_21.data.util.NetworkConnectivityUtil
import com.example.homework_21.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHandleResource(): HandleResource = HandleResource()
    @Provides
    @Singleton
    fun provideProductLocalDataSource(productDao: ProductDao): ProductLocalDataSource =
        ProductLocalDataSourceImpl(dao = productDao)

    @Provides
    @Singleton
    fun provideProductRemoteDataSource(productService: ProductService, handleResource: HandleResource): ProductRemoteDataSource =
        ProductRemoteDataSourceImpl(productService = productService, handleResource = handleResource)



    @Provides
    @Singleton
    fun provideProductRepository(
        productLocalDataSource: ProductLocalDataSource,
        productRemoteDataSource: ProductRemoteDataSource,
        networkConnectivityUtil: NetworkConnectivityUtil
    ): ProductRepository =
        ProductRepositoryImpl(
            productLocalDataSource = productLocalDataSource,
            productRemoteDataSource = productRemoteDataSource,
            networkConnectivityUtil = networkConnectivityUtil
        )
}