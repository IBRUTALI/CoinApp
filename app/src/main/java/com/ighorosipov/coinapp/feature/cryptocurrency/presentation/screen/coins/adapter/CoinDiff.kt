package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency

class CoinDiff(
    private val oldList: List<Cryptocurrency>,
    private val newList: List<Cryptocurrency>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].image != newList[newItemPosition].image -> {
                false
            }

            oldList[oldItemPosition].symbol != newList[newItemPosition].symbol -> {
                false
            }

            oldList[oldItemPosition].currentPrice != newList[newItemPosition].currentPrice -> {
                false
            }

            oldList[oldItemPosition].currencySymbol != newList[newItemPosition].currencySymbol -> {
                false
            }

            oldList[oldItemPosition].priceChangePercentage24h != newList[newItemPosition].priceChangePercentage24h -> {
                false
            }

            else -> true
        }
    }

}