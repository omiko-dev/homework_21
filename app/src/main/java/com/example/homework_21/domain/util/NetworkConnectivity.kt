package com.example.homework_21.domain.util

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivity {
    fun observe(): Flow<Status>

    enum class Status {
        Available, Lost
    }
}