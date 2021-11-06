package com.kcruz.cryptochallenge.presentation.currencies

//TODO: Add documentation

sealed class CurrenciesEvent

data class SendMessage(val message: String): CurrenciesEvent()
