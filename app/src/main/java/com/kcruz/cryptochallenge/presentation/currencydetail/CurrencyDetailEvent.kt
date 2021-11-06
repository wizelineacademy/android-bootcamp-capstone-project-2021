package com.kcruz.cryptochallenge.presentation.currencydetail

//TODO: Add documentation

sealed class CurrencyDetailEvent

data class SendMessage(val message: String): CurrencyDetailEvent()
