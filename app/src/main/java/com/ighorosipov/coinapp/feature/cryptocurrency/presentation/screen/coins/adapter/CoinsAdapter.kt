package com.ighorosipov.coinapp.feature.cryptocurrency.presentation.screen.coins.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ighorosipov.coinapp.R
import com.ighorosipov.coinapp.databinding.ItemCryptocurrencyBinding
import com.ighorosipov.coinapp.feature.cryptocurrency.domain.model.Cryptocurrency

class CoinsAdapter: RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var cryptocurrencies = emptyList<Cryptocurrency>()

    class CoinsViewHolder(val binding: ItemCryptocurrencyBinding) : ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CoinsViewHolder {
        val binding = ItemCryptocurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(cryptocurrencies[holder.adapterPosition].image)
                .into(imageCoin)
            coinName.text = cryptocurrencies[holder.adapterPosition].id
            coinSymbol.text = cryptocurrencies[holder.adapterPosition].symbol
            "${cryptocurrencies[holder.adapterPosition].currencySymbol} ${cryptocurrencies[holder.adapterPosition].currentPrice}".apply {
                coinPrice.text = this
            }
            val coinPriceChangeInPercentage = if (cryptocurrencies[holder.adapterPosition].priceChangePercentage24h < 0) {
                coinPriceChange.setTextColor(holder.itemView.context.getColor(R.color.text_red))
                "${cryptocurrencies[holder.adapterPosition].priceChangePercentage24h}%"
            } else {
                coinPriceChange.setTextColor(holder.itemView.context.getColor(R.color.text_green))
                "+ ${cryptocurrencies[holder.adapterPosition].priceChangePercentage24h}%"
            }
            coinPriceChange.text = coinPriceChangeInPercentage
        }

    }

    override fun getItemCount(): Int {
        return cryptocurrencies.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onCoinClick(position: Int, cryptocurrency: Cryptocurrency)
    }

    fun setList(newList: List<Cryptocurrency>) {
        val diffUtil = CoinDiff(cryptocurrencies, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        cryptocurrencies = newList
    }

}