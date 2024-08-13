package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins

import android.view.View
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.ighorosipov.coinapp.databinding.FragmentCoinsBinding
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.adapter.CoinsAdapter
import com.ighorosipov.coinapp.util.base.BaseFragment
import com.ighorosipov.coinapp.util.di.lazyViewModel
import kotlinx.coroutines.launch

class CoinsFragment : BaseFragment<FragmentCoinsBinding, CoinsViewModel>(
    FragmentCoinsBinding::inflate
) {

    private val coinsAdapter by lazy { CoinsAdapter() }

    override val viewModel: CoinsViewModel by lazyViewModel {
        requireContext().appComponent().hotelListViewModel().create()
    }

    override fun inject() {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        initCoinsAdapter()
        repeatConnection()
        chipGroupCheckChange()
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
                viewModel.cryptocurrencies.collect { cryptocurrencies ->
                    binding.groupSuccess.visibility = View.VISIBLE
                    coinsAdapter.setList(cryptocurrencies)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentCurrency.collect { currency ->
                  val checkId = when(currency) {
                        Currency.USD -> binding.chipUsd.id
                        Currency.RUB -> binding.chipRub.id
                    }
                    binding.chipGroup.check(checkId)
                }
            }
        }
    }

    private fun initCoinsAdapter() {
        binding.cryptocurrencies.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun repeatConnection() {
        binding.btnTryAgain.setOnClickListener {
            viewModel.onEvent(CoinsScreenEvent.GetCurrencies)
        }
    }

    private fun chipGroupCheckChange() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val checkCurrency = (group[checkedIds[0]] as Chip).text.toString()
            viewModel.onEvent(CoinsScreenEvent.ChangeCurrency(Currency.valueOf(checkCurrency)))
        }
    }
}