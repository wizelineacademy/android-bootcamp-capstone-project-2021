package com.example.cryptochallenge.data.model

import java.lang.Exception

data class GetBookDetailFailure(val exception: Exception): GetBookDetailEvent()