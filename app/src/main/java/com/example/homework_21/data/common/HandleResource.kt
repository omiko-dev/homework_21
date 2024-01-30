package com.example.homework_21.data.common

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HandleResource {
    fun<T> handleResource(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Loader(loader = true))
                val result = call()
                if(result.isSuccessful){
                    emit(Resource.Success(success = result.body()!!))
                }else {
                    emit(Resource.Error(error = "error"))
                }
            }catch (e: Exception){
                Log.i("omiko", e.message!!)
            }finally {
                emit(Resource.Loader(loader = false))
            }
        }
    }
}