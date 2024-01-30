package com.example.homework_21.presentation.screen.product.event

sealed class ProductEvent {
    data object GetProductList: ProductEvent()
}