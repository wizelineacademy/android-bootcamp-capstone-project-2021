package com.example.cryptochallenge.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.ItemCryptocurrencyBinding
import com.example.cryptochallenge.domain.CryptoCurrencyType
import com.example.cryptochallenge.domain.availablebook.Payload
import com.example.cryptochallenge.ui.Extensions.Companion.MAJOR
import com.example.cryptochallenge.ui.Extensions.Companion.MINOR
import com.example.cryptochallenge.ui.Extensions.Companion.getMajorAndMinor
import com.example.cryptochallenge.ui.Extensions.Companion.loadCurrencyImg
import java.lang.Exception

class CryptocurrencyViewHolder(private val binding: ItemCryptocurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val resources = binding.root.resources

    fun bind(item: Payload) {
        setMajorAndMinorImg(item.book ?: "")
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        binding.root.setOnClickListener(listener)
    }

    private fun setMajorAndMinorImg(bookName: String) {
        val values = bookName.getMajorAndMinor()

        values?.get(MAJOR)?.let {
            val currency = try {
                CryptoCurrencyType.valueOf(it.uppercase())
            } catch (exception: Exception) {
                CryptoCurrencyType.DEFAULT
            }

            binding.tvCryptoNameMajor.text = resources.getString(currency.realNameId)
            binding.ivCryptoImageMajor.loadCurrencyImg(currency.imgId)
        }

        values?.get(MINOR)?.let {
            val currency = try {
                CryptoCurrencyType.valueOf(it.uppercase())
            } catch (exception: Exception) {
                CryptoCurrencyType.DEFAULT
            }

            binding.tvCryptoNameMinor.text = resources.getString(currency.realNameId)
            binding.ivCryptoImageMinor.loadCurrencyImg(currency.imgId)
        }

    }
}