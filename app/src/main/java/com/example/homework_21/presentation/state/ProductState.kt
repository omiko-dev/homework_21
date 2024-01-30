package com.example.homework_21.presentation.state

import com.example.homework_21.presentation.model.ProductUI

data class ProductState (
    var loader: Boolean = false,
    var products: List<ProductUI>? = null,
    var error: Int? = null
)