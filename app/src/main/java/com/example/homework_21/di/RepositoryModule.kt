package com.example.homework_21.di

import com.example.homework_21.data.common.HandleResource
import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.local.repository.ProductDbRepositoryImpl
import com.example.homework_21.data.remote.repository.ProductRepositoryImpl
import com.example.homework_21.data.remote.service.ProductService
import com.example.homework_21.domain.local.repository.ProductDbRepository
import com.example.homework_21.domain.remote.repository.ProductRepository
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
    fun provideProductRepository(productService: ProductService, handleResource: HandleResource): ProductRepository =
        ProductRepositoryImpl(productService = productService, handleResource = handleResource)

    @Provides
    @Singleton
    fun provideProductDbRepository(productDao: ProductDao): ProductDbRepository =
        ProductDbRepositoryImpl(productDao = productDao)
}