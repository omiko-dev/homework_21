package com.example.homework_21.data.model

data class ProductResponse (
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean,
    val category: String
)