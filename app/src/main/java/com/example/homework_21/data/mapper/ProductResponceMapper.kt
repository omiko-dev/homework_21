package com.example.homework_21.data.mapper

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.data.model.ProductResponse
import com.example.homework_21.data.remote.model.NetworkProduct

fun ProductEntity.asExternalModel() =
    ProductResponse (
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category ?: ""
    )

fun NetworkProduct.asExternalModel() =
    ProductResponse (
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )