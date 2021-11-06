package com.example.cryptochallenge.di.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptochallenge.data.source.ICryptoSource
import com.example.cryptochallenge.di.client.NetworkClient
import com.example.cryptochallenge.domain.availablebook.AvailableBook
import com.example.cryptochallenge.domain.availablebook.Payload
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Perform the calls to web services and returns responses
 */
class CryptoSource : ICryptoSource {
    /**
     * Property for dispose web service calls when fragment destroy
     */
    private val compositeDisposable = CompositeDisposable()

    override fun getAvailableBooks(): LiveData<List<Payload>?> {
        val response = MutableLiveData<List<Payload>?>()

        val call = NetworkClient().getCryptoDetailService().getAvailableBooks()

        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response.postValue(it.payload)
                }, {
                    response.postValue(null)
                })
        )
        return response
    }

    override fun getTicker(bookName: String): LiveData<com.example.cryptochallenge.domain.ticker.Payload?> {
        val response = MutableLiveData<com.example.cryptochallenge.domain.ticker.Payload?>()
        val data: MutableMap<String, String> = mutableMapOf(BOOK to bookName)
        val call = NetworkClient().getCryptoDetailService().getTicker(data)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response.postValue(it.payload)
                }, {
                    response.postValue(null)
                })
        )
        return response
    }

    override fun getOrderBook(bookName: String): LiveData<com.example.cryptochallenge.domain.orderbook.Payload?> {
        val response = MutableLiveData<com.example.cryptochallenge.domain.orderbook.Payload?>()
        val data: MutableMap<String, String> = mutableMapOf(BOOK to bookName)
        val call = NetworkClient().getCryptoDetailService().getOrderBook(data)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response.postValue(it.payload)
                }, {
                    response.postValue(null)
                })
        )
        return response
    }

    override fun clear() {
        compositeDisposable.clear()
    }

    companion object {
        /**
         * Property to send book name to web service call
         */
        private const val BOOK = "book"
    }
}