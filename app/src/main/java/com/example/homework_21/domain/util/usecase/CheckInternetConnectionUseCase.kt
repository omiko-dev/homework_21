package com.example.homework_21.domain.util.usecase

import com.example.homework_21.domain.util.NetworkConnectivity
import javax.inject.Inject

class CheckInternetConnectionUseCase @Inject constructor(
    private val networkConnectivity: NetworkConnectivity
) {
    operator fun invoke() = networkConnectivity.observe()
}