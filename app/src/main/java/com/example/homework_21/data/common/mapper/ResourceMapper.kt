package com.example.homework_21.data.common.mapper

import com.example.homework_21.data.common.Resource

fun <T, R> Resource<T>.resourceMapper(mapper: (T) -> R): Resource<R> {
    return when(this){
        is Resource.Success -> Resource.Success(success = mapper(success))
        is Resource.Error -> Resource.Error(error = error)
        is Resource.Loader -> Resource.Loader(loader = loader)
    }
}