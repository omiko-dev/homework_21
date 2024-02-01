package com.example.homework_21.data.remote.service

import com.example.homework_21.data.remote.model.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getProductList(): Response<List<NetworkProduct>>
}