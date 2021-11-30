package com.alexbar10.cryptotrack.ui.cryptoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexbar10.cryptotrack.database.entities.OrderEntity
import com.alexbar10.cryptotrack.database.repo.CryptoDBRepo
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Failure
import com.alexbar10.cryptotrack.domain.Order
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.Success
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
import com.alexbar10.cryptotrack.networking.repo.CryptosRxRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyDetailsViewModel @Inject constructor(
    private val cryptocurrenciesRepo: CryptocurrenciesRepo,
    private val cryptosRxRepo: CryptosRxRepo,
    private val repoDBRepo: CryptoDBRepo
) : ViewModel() {

    private val orderTypeAsk = 1
    private val orderTypeBid = 2
    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> get() = _errorLiveData

    private val _cryptocurrencyOrderLiveData = MutableLiveData<OrderResponse>()
    val cryptocurrencyOrderLiveData: LiveData<OrderResponse> get() = _cryptocurrencyOrderLiveData

    // Using RxJava
    fun getOrderRxFor(cryptocurrency: Cryptocurrency) {
        _loadingLiveData.postValue(true)

        cryptosRxRepo.getOrderFor(cryptocurrency.book)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleResponse(cryptocurrency))
    }

    private fun handleResponse(cryptocurrency: Cryptocurrency): Observer<OrderResponse> {
        return object : Observer<OrderResponse> {
            override fun onSubscribe(d: Disposable) {
                _loadingLiveData.postValue(true)
            }

            override fun onNext(orderResponse: OrderResponse) {
                _cryptocurrencyOrderLiveData.postValue(orderResponse)
                viewModelScope.launch(Dispatchers.IO) {
                    syncDB(orderResponse.payload, cryptocurrency)
                }
            }

            override fun onError(error: Throwable) {
                _errorLiveData.postValue(error)
            }

            override fun onComplete() {
                _loadingLiveData.postValue(false)
            }
        }
    }

    // Using coroutines
    fun getOrderFor(cryptocurrency: Cryptocurrency) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            val orderResponse = cryptocurrenciesRepo.getOrderFor(cryptocurrency.book)
            _loadingLiveData.postValue(false)

            if (orderResponse is Success) {
                _cryptocurrencyOrderLiveData.postValue(orderResponse.data)

                // Save in database
                orderResponse.data?.let {
                    viewModelScope.launch(Dispatchers.IO) {
                        syncDB(it.payload, cryptocurrency)
                    }
                }
            } else {
                val failure = orderResponse as Failure
                _errorLiveData.postValue(failure.error)
            }
        }
    }

    private suspend fun syncDB(order: Order, cryptocurrency: Cryptocurrency) {
        // Delete previous entries
        repoDBRepo.deleteLocalOrdersFor(cryptocurrency.book)

        // Parse order objects
        val orderEntities = mutableListOf<OrderEntity>()

        order.asks.forEach {
            val tempOrderEntity = OrderEntity(
                0,
                book = cryptocurrency.book,
                price = it.price,
                amount = it.amount,
                type = orderTypeAsk
            )
            orderEntities.add(tempOrderEntity)
        }
        order.bids.forEach {
            val tempOrderEntity = OrderEntity(
                0,
                book = cryptocurrency.book,
                price = it.price,
                amount = it.amount,
                type = orderTypeBid
            )
            orderEntities.add(tempOrderEntity)
        }

        // Save the data
        repoDBRepo.insertLocalOrders(orderEntities)
    }

    fun getLocalOrders(currency: Cryptocurrency) {
        viewModelScope.launch {

            repoDBRepo.getLocalOrdersFor(currency.book).collect {
                val orderBids = mutableListOf<OrderDetailItem>()
                val orderAsks = mutableListOf<OrderDetailItem>()

                it?.forEach { orderEntity ->
                    if (orderEntity.type == orderTypeAsk) {
                        orderAsks.add(
                            OrderDetailItem(
                                currency.book,
                                orderEntity.price,
                                orderEntity.amount
                            )
                        )
                    } else if (orderEntity.type == orderTypeBid) {
                        orderBids.add(
                            OrderDetailItem(
                                currency.book,
                                orderEntity.price,
                                orderEntity.amount
                            )
                        )
                    }
                }

                _loadingLiveData.postValue(false)
                _cryptocurrencyOrderLiveData.postValue(
                    OrderResponse(
                        true,
                        Order(
                            "",
                            orderBids,
                            orderAsks
                        )
                    )
                )
            }
        }
    }
}
