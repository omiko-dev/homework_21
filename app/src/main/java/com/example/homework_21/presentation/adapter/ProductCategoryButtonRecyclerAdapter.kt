package com.example.homework_21.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_21.R
import com.example.homework_21.databinding.ProductCategoryButtonBinding
import com.example.homework_21.presentation.model.CategoryUI

class ProductCategoryButtonRecyclerAdapter :
    RecyclerView.Adapter<ProductCategoryButtonRecyclerAdapter.ProductCategoryButtonViewHolder>() {
    inner class ProductCategoryButtonViewHolder(private val binding: ProductCategoryButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.btnCategory.apply {
                val item = categoryList[adapterPosition]
                text = item.categoryTitle

                if (item.isCheck) {
                    background = ContextCompat.getDrawable(context, R.drawable.category_button_on)
                    setTextColor(Color.WHITE)
                } else {
                    background = ContextCompat.getDrawable(context, R.drawable.category_button_off)
                    setTextColor(ContextCompat.getColor(context, R.color.white_blue_gray))
                }
            }
        }

        fun setListener() {
            binding.btnCategory.setOnClickListener {
                if (adapterPosition != lastPosition) {
                    categoryList[lastPosition].isCheck = false
                    notifyItemChanged(lastPosition)
                    lastPosition = adapterPosition
                    categoryList[lastPosition].isCheck = true
                    notifyItemChanged(lastPosition)

                    onClick(categoryList[adapterPosition].categoryValue)
                }
            }
        }
    }

    private var categoryList: MutableList<CategoryUI> = mutableListOf(
        CategoryUI(
            categoryTitle = "All",
            categoryValue = null,
            isCheck = true,
        )
    )
    private var lastPosition = 0
    lateinit var onClick: (String?) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCategoryButtonViewHolder {
        return ProductCategoryButtonViewHolder(
            ProductCategoryButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.count()
    }

    override fun onBindViewHolder(holder: ProductCategoryButtonViewHolder, position: Int) {
        with(holder) {
            bind()
            setListener()
        }
    }

    fun addCategories(newCategoryList: List<CategoryUI>) {
        newCategoryList.forEach { newList ->
            if (!categoryList.any { it.categoryValue == newList.categoryValue }) {
                categoryList.add(newList)
                notifyItemChanged(categoryList.count())
            }
        }
    }
}