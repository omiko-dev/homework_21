package com.example.homework_21.presentation.state
import com.example.homework_21.presentation.model.CategoryUI

data class CategoryState(
    var loader: Boolean = false,
    var categories: List<CategoryUI>? = null,
    var error: Int? = null
)
