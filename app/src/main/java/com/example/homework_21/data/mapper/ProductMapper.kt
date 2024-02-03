package com.example.homework_21.data.mapper

import com.example.homework_21.data.model.ProductResponse
import com.example.homework_21.domain.model.Product

fun ProductResponse.toDomain() =
    Product(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )