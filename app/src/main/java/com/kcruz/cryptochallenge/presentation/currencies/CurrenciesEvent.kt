package com.kcruz.cryptochallenge.presentation.currencies

sealed class CurrenciesEvent

data class SendMessage(val message: String): CurrenciesEvent()
