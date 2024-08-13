package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coin_detail

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.ighorosipov.coinapp.databinding.FragmentCoinDetailBinding
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.CryptocurrencyDetail
import com.ighorosipov.coinapp.util.base.BaseFragment
import kotlinx.coroutines.launch

class CoinDetailFragment: BaseFragment<FragmentCoinDetailBinding, CoinDetailViewModel>(
    FragmentCoinDetailBinding::inflate
) {
    override val viewModel: CoinDetailViewModel
        get() = TODO("Not yet implemented")

    override fun inject() {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        repeatConnection()
    }

    override fun subscribeToObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isError.collect { isError ->
                    if (isError) {
                        binding.groupSuccess.visibility = View.GONE
                        binding.groupError.visibility = View.VISIBLE
                    } else {
                        binding.groupError.visibility = View.GONE
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        binding.groupSuccess.visibility = View.GONE
                        binding.groupLoading.visibility = View.VISIBLE
                    } else {
                        binding.groupLoading.visibility = View.GONE
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cryptocurrency.collect { cryptocurrency ->
                    binding.groupSuccess.visibility = View.VISIBLE
                    if (cryptocurrency != null) {
                        setupUI(cryptocurrency)
                    }
                }
            }
        }
    }

    private fun setupUI(cryptocurrency: CryptocurrencyDetail) {
        binding.apply {
            Glide.with(requireContext())
                .load(cryptocurrency.image.large)
                .into(imageCoin)
            description.text = cryptocurrency.description.en
            category.text = cryptocurrency.categories.joinToString(",")
        }
    }

    private fun repeatConnection() {
        binding.btnTryAgain.setOnClickListener {
            viewModel.onEvent(CoinDetailEvent.GetCurrencyDetail)
        }
    }

}