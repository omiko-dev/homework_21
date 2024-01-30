package com.example.homework_21.presentation.screen.product

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_21.databinding.FragmentProductBinding
import com.example.homework_21.presentation.adapter.ProductCardRecyclerAdapter
import com.example.homework_21.presentation.base.BaseFragment
import com.example.homework_21.presentation.screen.product.event.ProductEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {
    private lateinit var adapter: ProductCardRecyclerAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun bind() {
        bindProductCardRecycler()
    }

    override fun event() {
        viewModel.onEvent(ProductEvent.GetProductList)
    }

    override fun observe() {
        productObserve()
    }

    private fun bindProductCardRecycler() {
        adapter = ProductCardRecyclerAdapter()
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun productObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productListStateFlow.collect {
                    with(it) {
                        products?.let { productList ->
                            adapter.submitList(productList)
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

    private fun showLoader(isActive: Boolean){
        with(binding) {
            if (isActive)
                loader.visibility = View.VISIBLE
            else
                loader.visibility = View.GONE
        }
    }
}