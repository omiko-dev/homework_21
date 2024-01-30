package com.example.homework_21.data.local.mapper

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.domain.model.ProductModel

fun ProductModel.toData() =
    ProductEntity(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )