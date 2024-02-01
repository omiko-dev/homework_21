package com.example.homework_21.data.mapper

import com.example.homework_21.data.local.entity.ProductEntity
import com.example.homework_21.data.remote.model.NetworkProduct
import com.example.homework_21.domain.model.Product

fun ProductEntity.asExternalModel() =
    Product (
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )

fun NetworkProduct.asExternalModel() =
    Product (
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )