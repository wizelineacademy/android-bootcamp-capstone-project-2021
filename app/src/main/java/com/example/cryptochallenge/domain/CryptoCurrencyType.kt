package com.example.cryptochallenge.domain

import com.example.cryptochallenge.R

/**
 * List of Cryptocurrency Types
 *
 * @property realNameId ID that represent the real name of the specific cryptocurrency
 * @property imgId ID that represent the image of the cryptocurrency
 */
enum class CryptoCurrencyType(val realNameId: Int, val imgId: Int) {
    BTC(R.string.btc_name, R.drawable.btc),
    MXN(R.string.mxn_name, R.drawable.mxn),
    ETH(R.string.eth_name, R.drawable.eth),
    XRP(R.string.xrp_name, R.drawable.xrp),
    LTC(R.string.ltc_name, R.drawable.ltc),
    BCH(R.string.bch_name, R.drawable.bch),
    TUSD(R.string.tusd_name, R.drawable.tusd),
    MANA(R.string.mana_name, R.drawable.mana),
    BAT(R.string.bat_name, R.drawable.bat),
    ARS(R.string.ars_name, R.drawable.ars),
    DAI(R.string.dai_name, R.drawable.dai),
    USD(R.string.usd_name, R.drawable.usd),
    BRL(R.string.brl_name, R.drawable.brl),
    USDT(R.string.usdt_name, R.drawable.usdt),
    COMP(R.string.comp_name, R.drawable.comp),
    LINK(R.string.link_name, R.drawable.link),
    UNI(R.string.uni_name, R.drawable.uni),
    AAVE(R.string.aave_name, R.drawable.aave),
    DEFAULT(R.string.default_name, R.drawable.cryptodefault)
}