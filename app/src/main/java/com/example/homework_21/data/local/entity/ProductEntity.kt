package com.example.homework_21.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean
)