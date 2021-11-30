package com.alexbar10.cryptotrack.networking.repo

import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.networking.services.CryptosRxServices
import io.reactivex.Observable
import javax.inject.Inject

// @Singleton
class CryptosRxRepo @Inject constructor(
    private val remoteApiServiceRx: CryptosRxServices
) {
    fun getOrderFor(currency: String): Observable<OrderResponse> = remoteApiServiceRx.getOrderFor(currency)
}
