package com.example.homework_21.data.mapper

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.domain.model.Product

fun Product.asEntity() =
    ProductEntity(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )