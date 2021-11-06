package com.example.cryptochallenge.ui.cryptodetail.adapter

import com.example.cryptochallenge.databinding.ItemHeaderDetailBinding
import com.example.cryptochallenge.domain.CryptoCurrencyType
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.ui.Extensions
import com.example.cryptochallenge.ui.Extensions.Companion.getMajorAndMinor
import com.example.cryptochallenge.ui.Extensions.Companion.loadCurrencyImg
import com.example.cryptochallenge.ui.commons.BaseHolder
import java.lang.Exception

/**
 * ViewHolder for Header Detail
 *
 * @property binding Item' view
 */
class HeaderDetailViewHolder(private val binding: ItemHeaderDetailBinding) :
    BaseHolder<DetailSectionItem>(binding.root) {

    override fun bind(item: DetailSectionItem?) {
        if (item !is DetailSectionItem && item?.content !is String)
            return

        val cryptoName = item.content as String

        val values = cryptoName.getMajorAndMinor()

        values?.get(Extensions.MAJOR)?.let {
            val currency = try {
                CryptoCurrencyType.valueOf(it.uppercase())
            } catch (exception: Exception) {
                CryptoCurrencyType.DEFAULT
            }

            binding.tvCryptoNameMajor.text = resources.getString(currency.realNameId)
            binding.ivCryptoImageMajor.loadCurrencyImg(currency.imgId)
        }

        values?.get(Extensions.MINOR)?.let {
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