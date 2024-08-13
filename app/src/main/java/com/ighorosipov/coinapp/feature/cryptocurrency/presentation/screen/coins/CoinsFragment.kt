package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.ighorosipov.coinapp.R
import com.ighorosipov.coinapp.databinding.FragmentCoinsBinding
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency
import com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.adapter.CoinsAdapter
import com.ighorosipov.coinapp.util.Constants.BUNDLE_COIN_ID
import com.ighorosipov.coinapp.util.Currency
import com.ighorosipov.coinapp.util.base.BaseFragment
import com.ighorosipov.coinapp.util.di.appComponent
import com.ighorosipov.coinapp.util.di.lazyViewModel
import kotlinx.coroutines.launch

class CoinsFragment : BaseFragment<FragmentCoinsBinding, CoinsViewModel>(
    FragmentCoinsBinding::inflate
) {

    private val coinsAdapter by lazy { CoinsAdapter() }

    override val viewModel: CoinsViewModel by lazyViewModel {
        requireContext().appComponent().coinsViewModel().create()
    }

    override fun inject() {
        requireContext().appComponent().inject(this)
    }

    override fun initViews() {
        initCoinsAdapter()
        repeatConnection()
        chipGroupCheckChange()
        onCoinClick()
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
        binding.chipGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedChip = group.findViewById<Chip>(group.checkedChipId)
            val checkCurrency = checkedChip.text.toString()
            viewModel.onEvent(CoinsScreenEvent.ChangeCurrency(Currency.valueOf(checkCurrency)))
        }
    }

    private fun onCoinClick() {
        coinsAdapter.setOnClickListener(object: CoinsAdapter.OnClickListener {
            override fun onCoinClick(position: Int, cryptocurrency: Cryptocurrency) {
                val bundle = bundleOf(BUNDLE_COIN_ID to cryptocurrency.id)
                findNavController().navigate(
                    R.id.action_coinsFragment_to_coinDetailFragment,
                    bundle
                )
            }
        })
    }

}