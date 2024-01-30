package com.example.homework_21.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_21.R
import com.example.homework_21.databinding.ProductCardBinding
import com.example.homework_21.presentation.extension.loadImagesWithGlide
import com.example.homework_21.presentation.model.ProductUI

class ProductCardRecyclerAdapter :
    ListAdapter<ProductUI, ProductCardRecyclerAdapter.ProductCardViewHolder>(ProductDiffUtil) {

    companion object {
        private val ProductDiffUtil = object : DiffUtil.ItemCallback<ProductUI>() {
            override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ProductCardViewHolder(private val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                ivProduct.loadImagesWithGlide(item.cover, 15)
                tvPrice.text = item.price
                tvProductName.text = item.title

                if (item.favorite)
                    ibFavorite.setImageResource(R.drawable.ic_heart)
                else
                    ibFavorite.setImageResource(R.drawable.ic_heart_out)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        return ProductCardViewHolder(
            ProductCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductCardViewHolder, position: Int) {
        holder.bind()
    }
}