package com.alexbar10.cryptotrack.ui.cryptoAvailable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexbar10.cryptotrack.databinding.CryptocurrencyItemBinding
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.utils.currencyFormat
import com.alexbar10.cryptotrack.utils.getImageResourceFor
import com.alexbar10.cryptotrack.utils.getMarketFor
import com.alexbar10.cryptotrack.utils.getNameFor

typealias OnCryptocurrencySelected = (Cryptocurrency) -> Unit

class CryptocurrenciesAdapter(
    private val onCryptocurrencySelected: OnCryptocurrencySelected
): RecyclerView.Adapter<CryptocurrenciesAdapter.CryptocurrencyViewHolder>() {

    private val cryptocurrenciesList: MutableList<Cryptocurrency> = mutableListOf()

    fun setData(cryptocurrencies: List<Cryptocurrency>) {
        cryptocurrenciesList.clear()
        cryptocurrenciesList.addAll(cryptocurrencies)
        notifyDataSetChanged()
    }

    override fun getItemCount() = cryptocurrenciesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        return LayoutInflater.from(parent.context)
            .let { layoutInflater -> CryptocurrencyItemBinding.inflate(layoutInflater, parent, false) }
            .let { binding -> CryptocurrencyViewHolder(binding, onCryptocurrencySelected) }
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        holder.bind(cryptocurrenciesList[position])
    }

    class CryptocurrencyViewHolder(
        private val binding: CryptocurrencyItemBinding,
        private val onCryptocurrencySelected: OnCryptocurrencySelected
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptocurrency: Cryptocurrency) {
            binding.currencyNameLabel.text = binding.root.context.getString(getNameFor(cryptocurrency))
            binding.currencyMarketLabel.text = getMarketFor(cryptocurrency).uppercase()
            binding.currencyImageView.setImageResource(getImageResourceFor(cryptocurrency))
            cryptocurrency.ticker?.last.let {
                binding.currencyCostLabel.text = currencyFormat(cryptocurrency) + " " + getMarketFor(cryptocurrency).uppercase()
            }

            binding.cardView.setOnClickListener { onCryptocurrencySelected(cryptocurrency) }
        }
    }
}
