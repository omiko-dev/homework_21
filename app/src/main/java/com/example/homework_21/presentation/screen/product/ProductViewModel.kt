package com.example.homework_21.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_21.R
import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.local.usecase.ProductLocalUseCase
import com.example.homework_21.domain.remote.usecase.ProductRemoteUseCase
import com.example.homework_21.domain.util.NetworkConnectivity
import com.example.homework_21.domain.util.usecase.CheckInternetConnectionUseCase
import com.example.homework_21.presentation.model.ProductUI
import com.example.homework_21.presentation.screen.product.event.ProductEvent
import com.example.homework_21.presentation.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productLocalUseCase: ProductLocalUseCase,
    private val productRemoteUseCase: ProductRemoteUseCase,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : ViewModel() {
    private var _productListStateFlow = MutableStateFlow(ProductState())
    val productListStateFlow = _productListStateFlow.asStateFlow()


    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.GetProductList -> getProduct()
        }
    }


    private fun getProduct() {
        viewModelScope.launch {
            checkInternetConnectionUseCase().collect {
                when (it) {
                    NetworkConnectivity.Status.Available -> {
                        getRemoteProductList()
                    }

                    NetworkConnectivity.Status.Lost -> {
                        getLocalProductList()
                    }
                }
            }
        }
    }

    private fun getLocalProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            productLocalUseCase.getProductListDbUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _productListStateFlow.update { productList ->
                            productList.copy(
                                products = it.success
                            )
                        }
                    }

                    is Resource.Error -> {
                        _productListStateFlow.update { productList ->
                            productList.copy(
                                error = R.string.not_found_items
                            )
                        }
                    }

                    is Resource.Loader -> {
                        _productListStateFlow.update { productList ->
                            productList.copy(
                                loader = it.loader
                            )
                        }
                    }
                }
            }
        }
    }

    private fun insertProduct(productList: List<ProductUI>) {
        viewModelScope.launch(Dispatchers.IO) {
            productLocalUseCase.insertProductListDbUseCase(productUIList = productList)
        }
    }

    private fun getRemoteProductList() {
        viewModelScope.launch {
            productRemoteUseCase.getProductListUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _productListStateFlow.update { productList ->
                            insertProduct(it.success)
                            productList.copy(
                                products = it.success
                            )
                        }
                    }

                    is Resource.Error -> {
                        _productListStateFlow.update { productList ->
                            productList.copy(
                                error = R.string.not_found_items
                            )
                        }
                    }

                    is Resource.Loader -> {
                        _productListStateFlow.update { productList ->
                            productList.copy(
                                loader = it.loader
                            )
                        }
                    }
                }
            }
        }
    }
}