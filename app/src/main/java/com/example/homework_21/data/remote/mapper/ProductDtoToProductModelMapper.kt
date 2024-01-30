package com.example.homework_21.data.remote.mapper

import com.example.homework_21.data.remote.model.ProductDto
import com.example.homework_21.domain.model.ProductModel

fun ProductDto.toDomain() =
    ProductModel(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )
