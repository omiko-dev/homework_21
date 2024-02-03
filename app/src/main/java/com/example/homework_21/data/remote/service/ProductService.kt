package com.example.homework_21.data.remote.service

import com.example.homework_21.data.remote.model.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getProductList(): Response<List<NetworkProduct>>
}