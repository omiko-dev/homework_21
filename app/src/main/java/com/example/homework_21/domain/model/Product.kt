package com.example.homework_21.domain.model

data class Product (
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean,
    val category: String
)