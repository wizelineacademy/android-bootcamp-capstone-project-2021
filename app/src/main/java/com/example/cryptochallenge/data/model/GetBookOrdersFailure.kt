package com.example.cryptochallenge.data.model

import java.lang.Exception

data class GetBookOrdersFailure(val exception: Exception): GetBookOrdersEvent()