package com.alexbar10.cryptotrack.utils

import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import java.text.DecimalFormat

const val prefix_btc = "btc"
const val prefix_eth = "eth"
const val prefix_xrp = "xrp"
const val prefix_ltc = "ltc"
const val prefix_bch = "bch"
const val prefix_tusd = "tusd"
const val prefix_mana = "mana"
const val prefix_bat = "bat"
const val prefix_dai = "dai"
const val prefix_usd = "usd"
const val prefix_comp = "comp"
const val prefix_link = "link"
const val prefix_uni = "uni"
const val prefix_aave = "aave"

fun getImageResourceFor(cryptocurrency: Cryptocurrency): Int {
    return when (getPrefixFor(cryptocurrency)) {
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

fun getNameFor(cryptocurrency: Cryptocurrency): Int {
    return when (getPrefixFor(cryptocurrency)) {
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

fun getMarketFor(cryptocurrency: Cryptocurrency) = getPostfixFor(cryptocurrency)

fun getMarketFor(order: OrderDetailItem) = order.book.substringAfter("_")

fun getPrefixFor(cryptocurrency: Cryptocurrency) = cryptocurrency.book.substringBefore("_")

fun getPostfixFor(cryptocurrency: Cryptocurrency) = cryptocurrency.book.substringAfter("_")

enum class PriceType {
    LAST,
    HIGH,
    LOW
}

fun currencyFormat(cryptocurrency: Cryptocurrency, forPrice: PriceType = PriceType.LAST): String? {
    cryptocurrency.ticker?.let {
        // Check if crypto is in bitcoin market, in that case use 8 decimals
        var formatter: DecimalFormat = if (getMarketFor(cryptocurrency) == prefix_btc) {
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

fun currencyFormat(order: OrderDetailItem, total: Double? = null): String? {
    // Check if crypto is in bitcoin market, in that case use 8 decimals
    var formatter: DecimalFormat = if (getMarketFor(order) == prefix_btc) {
        DecimalFormat("0.00000000")
    } else {
        DecimalFormat("###,###,##0.00")
    }

    return formatter.format(total ?: order.price)
}
