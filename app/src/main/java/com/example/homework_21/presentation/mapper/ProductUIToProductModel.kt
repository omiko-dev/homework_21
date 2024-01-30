package com.example.homework_21.presentation.mapper

import com.example.homework_21.domain.model.ProductModel
import com.example.homework_21.presentation.model.ProductUI

fun ProductUI.toDomain() =
    ProductModel(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )