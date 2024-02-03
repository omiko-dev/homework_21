package com.example.homework_21.presentation.screen.product.event

sealed class ProductEvent {
    data class GetProductList(val category: String? = null) : ProductEvent()
}