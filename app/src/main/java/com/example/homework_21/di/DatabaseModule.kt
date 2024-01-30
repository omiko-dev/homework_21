package com.example.homework_21.di

import android.content.Context
import androidx.room.Room
import com.example.homework_21.data.local.dao.ProductDao
import com.example.homework_21.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "product_database"
        ).build()

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao =
        appDatabase.productDao()
}