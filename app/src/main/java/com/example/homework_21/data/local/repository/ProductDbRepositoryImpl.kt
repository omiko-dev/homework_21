package com.example.homework_21.data.local.repository

import android.util.Log
import com.example.homework_21.data.common.Resource
import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.local.mapper.toData
import com.example.homework_21.data.local.mapper.toDomain
import com.example.homework_21.domain.model.ProductModel
import com.example.homework_21.domain.local.repository.ProductDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDbRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductDbRepository {
    override fun getAll(): Flow<Resource<List<ProductModel>>> =
        flow {
            try {
                emit(Resource.Loader(loader = true))
                val data = productDao.getAll().map { it.toDomain() }
                if (data.isEmpty())
                    emit(Resource.Success(success = data))
                else
                    emit(Resource.Error())
            } catch (e: Exception) {
                Log.i("e", e.message!!)
            } finally {
                emit(Resource.Loader(loader = false))
            }
        }


    override fun insertAll(vararg product: ProductModel) {
        val allProduct = productDao.getAll()
        product.forEach {
            if (!allProduct.contains(it.toData()))
                productDao.insertAll(it.toData())
        }
    }

    override fun delete(product: ProductModel) {
        productDao.delete(product = product.toData())
    }
}