package com.example.homework_21.data.mapper

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.data.model.ProductResponse

fun ProductResponse.asEntity() =
    ProductEntity(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )