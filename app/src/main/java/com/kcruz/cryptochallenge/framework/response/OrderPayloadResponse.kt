package com.kcruz.cryptochallenge.framework.response

import com.kcruz.cryptochallenge.domain.Order

//TODO: Add documentation

class OrderPayloadResponse(
    val asks: List<Order>,
    val bids: List<Order>,
    val updatedAt: String,
    val sequence: String
)