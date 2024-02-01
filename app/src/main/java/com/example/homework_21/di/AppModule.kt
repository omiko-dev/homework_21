package com.example.homework_21.di

import android.content.Context
import com.example.homework_21.BuildConfig
import com.example.homework_21.data.remote.service.ProductService
import com.example.homework_21.data.util.NetworkConnectivityUtil
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkConnectivityUtil(@ApplicationContext context: Context): NetworkConnectivityUtil =
        NetworkConnectivityUtil(context = context)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.URL_PRODUCT)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()


    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)
}