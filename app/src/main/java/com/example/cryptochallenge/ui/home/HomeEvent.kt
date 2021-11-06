package com.example.cryptochallenge.ui.home

sealed class HomeEvent {
    class OnShowCryptoDetail(val cryptoName: String) : HomeEvent()
}