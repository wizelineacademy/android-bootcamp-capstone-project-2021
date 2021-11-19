package com.example.bootcampproject.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.text.DecimalFormat

/*
enum class CurrencyNameEnum(val Currency: String) {
    Bitcoin("btc"), Ethereum, Xrp, Litecoin,
    Bitcoin_Cash, TrueUsd, Mana,
    Bat, Dai, Usd, Compound, Chain_Link,
    Uni_Swap, Aave,
}*/
private val listCurrencies = listOf<String>(
    "btc", "eth", "xrp", "ltc",
    "bch", "tusd", "mana", "bat", "dai",
    "usd", "comp", "link", "uni", "aave",
)
private const val CURRENCY_BASE_IMAGE_URL = "https://cryptoicon-api.vercel.app/api/icon/"
private const val GENERIC_CURRENCY_IMAGE_URL =
    "https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/green_prodpictdollar_1484336218-1.png"

fun String.attachedName(): String = when (this) {
    "btc" -> "bitcoin"
    "eth" -> "Ethereum"
    "xrp" -> "Xrp"
    "ltc" -> "Litecoin"
    "bch" -> "Bitcoin Cash"
    "tusd" -> "True Usd"
    "mana" -> "Mana"
    "bat" -> "Bat"
    "dai" -> "Dai"
    "usd" -> "Usd"
    "comp" -> "Compound"
    "link" -> "Chain Link"
    "uni" -> "Uni Swap"
    "aave" -> "Aave"
    else -> "Not exist"
}

fun Double.reformatNumber(): String = DecimalFormat("#.###########").format(this)
fun returnURLImage(code: String): String {
    return if (listCurrencies.contains(code))
        CURRENCY_BASE_IMAGE_URL + code
    else GENERIC_CURRENCY_IMAGE_URL
}

fun isOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}



