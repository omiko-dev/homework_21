package com.example.homework_21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}