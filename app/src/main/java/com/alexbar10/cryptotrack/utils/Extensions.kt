package com.alexbar10.cryptotrack.utils

import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import java.text.DecimalFormat

fun OrderDetailItem.getMarket() = this.book.substringAfter(DELIMITER_CHARACTER)
fun Cryptocurrency.getMarket() = this.book.substringAfter(DELIMITER_CHARACTER)

fun String.getPrefix() = this.substringBefore(DELIMITER_CHARACTER)

fun Cryptocurrency.getStringResource(): Int {
    return when (this.book.getPrefix()) {
        prefix_btc -> R.string.name_btc
        prefix_eth -> R.string.name_eth
        prefix_xrp -> R.string.name_xrp
        prefix_ltc -> R.string.name_ltc
        prefix_bch -> R.string.name_bch
        prefix_tusd -> R.string.name_tusd
        prefix_mana -> R.string.name_mana
        prefix_bat -> R.string.name_bat
        prefix_dai -> R.string.name_dai
        prefix_usd -> R.string.name_usd
        prefix_comp -> R.string.name_comp
        prefix_link -> R.string.name_link
        prefix_uni -> R.string.name_uni
        prefix_aave -> R.string.name_aave
        else -> R.string.name_btc
    }
}

fun Cryptocurrency.getImageResource(): Int {
    return when (this.book.getPrefix()) {
        prefix_btc -> R.drawable.bitcoin_btc_logo
        prefix_eth -> R.drawable.ethereum_eth_logo
        prefix_xrp -> R.drawable.xrp_xrp_logo
        prefix_ltc -> R.drawable.litecoin_ltc_logo
        prefix_bch -> R.drawable.bitcoin_cash_bch_logo
        prefix_tusd -> R.drawable.trueusd_tusd_logo
        prefix_mana -> R.drawable.decentraland_mana_logo
        prefix_bat -> R.drawable.basic_attention_token_bat_logo
        prefix_dai -> R.drawable.multi_collateral_dai_dai_logo
        prefix_usd -> R.drawable.tether_usdt_logo
        prefix_comp -> R.drawable.compound_comp_logo
        prefix_link -> R.drawable.chainlink_link_logo
        prefix_uni -> R.drawable.uniswap_uni_logo
        prefix_aave -> R.drawable.aave_aave_logo
        else -> R.drawable.bitcoin_btc_logo
    }
}

fun Cryptocurrency.currencyFormat(forPrice: PriceType = PriceType.LAST): String? {
    this.ticker?.let {
        // Check if crypto is in bitcoin market, in that case use 8 decimals
        var formatter: DecimalFormat = if (this.getMarket() == prefix_btc) {
            DecimalFormat("0.00000000")
        } else {
            DecimalFormat("###,###,##0.00")
        }

        return when (forPrice) {
            PriceType.LAST -> formatter.format(it.last)
            PriceType.HIGH -> formatter.format(it.high)
            PriceType.LOW -> formatter.format(it.low)
        }
    }
    return null
}

fun OrderDetailItem.currencyFormat(total: Double? = null): String? {
    // Check if crypto is in bitcoin market, in that case use 8 decimals
    var formatter: DecimalFormat = if (this.getMarket() == prefix_btc) {
        DecimalFormat("0.00000000")
    } else {
        DecimalFormat("###,###,##0.00")
    }

    return formatter.format(total ?: this.price)
}
