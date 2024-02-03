package com.example.homework_21.presentation.screen.product

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_21.databinding.FragmentProductBinding
import com.example.homework_21.presentation.adapter.ProductCardRecyclerAdapter
import com.example.homework_21.presentation.adapter.ProductCategoryButtonRecyclerAdapter
import com.example.homework_21.presentation.base.BaseFragment
import com.example.homework_21.presentation.screen.product.event.ProductEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {
    private lateinit var productAdapter: ProductCardRecyclerAdapter
    private lateinit var categoryAdapter: ProductCategoryButtonRecyclerAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun bind() {
        bindProductCardRecycler()
        bindProductCategoryButtonRecycler()
    }

    override fun event() {
        viewModel.onEvent(ProductEvent.GetProductList())
    }

    override fun observe() {
        productObserve()
        categoryObserve()
    }

    override fun listener() {
        categoryAdapter.onClick = {
            viewModel.onEvent(ProductEvent.GetProductList(it))
        }
    }

    private fun bindProductCardRecycler() {
        productAdapter = ProductCardRecyclerAdapter()
        with(binding) {
            productRecycler.adapter = productAdapter
            productRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun bindProductCategoryButtonRecycler() {
        categoryAdapter = ProductCategoryButtonRecyclerAdapter()
        with(binding) {
            categoryRecycler.adapter = categoryAdapter
            categoryRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun productObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productListStateFlow.collect {
                    with(it) {
                        products?.let { productList ->
                            productAdapter.submitList(productList)
                            error = null
                            binding.tvError.text = null
                        }
                        error?.let { id ->
                            binding.tvError.text = resources.getText(id)
                        }
                        showLoader(loader)
                    }
                }
            }
        }
    }

    private fun categoryObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productCategoryListStateFlow.collect { categoryState ->
                    with(categoryState) {
                        categories?.let {
                            categoryAdapter.addCategories(it)
                            binding.categoryRecycler.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showLoader(isActive: Boolean) {
        with(binding) {
            if (isActive)
                loader.visibility = View.VISIBLE
            else
                loader.visibility = View.GONE
        }
    }
}