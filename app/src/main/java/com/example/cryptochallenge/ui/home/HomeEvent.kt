package com.example.cryptochallenge.ui.home

/**
 * Handle home events
 */
sealed class HomeEvent {

    /**
     * Is triggered when an item is selected to show it's detail
     *
     * @param cryptoName Book record name
     */
    class OnShowCryptoDetail(val cryptoName: String) : HomeEvent()
}