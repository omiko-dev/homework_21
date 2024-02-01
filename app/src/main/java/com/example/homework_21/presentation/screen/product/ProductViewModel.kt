package com.example.homework_21.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_21.R
import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.usecase.GetProductUseCase
import com.example.homework_21.presentation.mapper.toPresenter
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
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {
    private var _productListStateFlow = MutableStateFlow(ProductState())
    val productListStateFlow = _productListStateFlow.asStateFlow()


    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.GetProductList -> getProduct()
        }
    }

    private fun getProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _productListStateFlow.update { state ->
                            state.copy(
                                products = resource.success.map { it.toPresenter() }
                            )
                        }
                    }

                    is Resource.Error -> {
                        _productListStateFlow.update { state ->
                            state.copy(
                                error = R.string.not_found_items
                            )
                        }
                    }

                    is Resource.Loader -> {
                        _productListStateFlow.update { state ->
                            state.copy(
                                loader = resource.loader
                            )
                        }
                    }
                }
            }
        }
    }
}