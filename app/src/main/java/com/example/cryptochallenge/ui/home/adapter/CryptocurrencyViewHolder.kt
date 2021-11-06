package com.example.cryptochallenge.ui.home.adapter

import android.view.View
import com.example.cryptochallenge.databinding.ItemCryptocurrencyBinding
import com.example.cryptochallenge.domain.CryptoCurrencyType
import com.example.cryptochallenge.domain.availablebook.Payload
import com.example.cryptochallenge.ui.Extensions.Companion.MAJOR
import com.example.cryptochallenge.ui.Extensions.Companion.MINOR
import com.example.cryptochallenge.ui.Extensions.Companion.getMajorAndMinor
import com.example.cryptochallenge.ui.Extensions.Companion.loadCurrencyImg
import com.example.cryptochallenge.ui.commons.BaseHolder
import java.lang.Exception

/**
 * ViewHolder for cryptocurrency records
 *
 * @property binding Item view
 */
class CryptocurrencyViewHolder(private val binding: ItemCryptocurrencyBinding) :
    BaseHolder<Payload>(binding.root) {

    override fun bind(item: Payload?) {
        if (item !is Payload)
            return

        setMajorAndMinorImg(item.book ?: "")
    }

    /**
     * Set listener for select an item
     */
    fun setOnClickListener(listener: View.OnClickListener) {
        binding.root.setOnClickListener(listener)
    }

    /**
     * Get Major and Minor currency and set them in view
     *
     * @param bookName Book name
     */
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