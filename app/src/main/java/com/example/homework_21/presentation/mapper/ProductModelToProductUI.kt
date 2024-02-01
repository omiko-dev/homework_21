package com.example.homework_21.presentation.mapper

import com.example.homework_21.domain.model.Product
import com.example.homework_21.presentation.model.ProductUI

fun Product.toPresenter() =
    ProductUI(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )