package com.kcruz.cryptochallenge.framework.response

import com.kcruz.cryptochallenge.domain.ExchangeOrderBook

class AvailableBooksResponse (
    val success: Boolean,
    val payload: List<ExchangeOrderBook>
)