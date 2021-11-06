package com.example.cryptochallenge.ui.cryptodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.data.repository.CryptoRepository
import com.example.cryptochallenge.domain.DetailSectionItem
import com.example.cryptochallenge.domain.SectionType
import com.example.cryptochallenge.domain.ticker.Payload
import com.example.cryptochallenge.ui.commons.SingleLiveEvent
import com.example.cryptochallenge.usecases.GetOrderBook
import com.example.cryptochallenge.usecases.GetTicker

class CryptoDetailViewModel : ViewModel() {
    private val cryptoRepository = CryptoRepository()

    private val _sections = SingleLiveEvent<MutableList<DetailSectionItem>>()
    val sections: LiveData<MutableList<DetailSectionItem>> get() = _sections

    private val _showLoader = SingleLiveEvent<Boolean>()
    val showLoader: LiveData<Boolean> get() = _showLoader

    fun getTicker(bookName: String): LiveData<Payload?> {
        return GetTicker(cryptoRepository).execute(bookName)
    }

    fun getOrderBook(bookName: String): LiveData<com.example.cryptochallenge.domain.orderbook.Payload?> {
        return GetOrderBook(cryptoRepository).execute(bookName)
    }

    fun setItem(item: Any?) {
        when (item) {
            is String -> addSection(DetailSectionItem(SectionType.HEADER, item))
            is Payload -> addSection(DetailSectionItem(SectionType.TICKER, item))
            is com.example.cryptochallenge.domain.orderbook.Payload -> {
                item.asks?.let { addSection(DetailSectionItem(SectionType.ASK, it)) }
                item.bids?.let { addSection(DetailSectionItem(SectionType.BID, it)) }
            }
        }
    }

    private fun addSection(section: DetailSectionItem) {
        var sections = _sections.value
        if (sections.isNullOrEmpty()) {
            sections = mutableListOf()
            sections.add(section)
        } else {
            val index = sections.indexOfFirst {
                it.type == section.type
            }
            if (index in sections.indices) {
                sections.removeAt(index)
                sections.add(index, section)
            } else {
                when (section.type) {
                    SectionType.HEADER -> sections.add(0, section)
                    SectionType.TICKER -> {
                        val index = getIndexOfSectionByType(SectionType.HEADER, sections)
                        if (index in sections.indices) {
                            sections.add(index + 1, section)
                        } else {
                            sections.add(0, section)
                        }
                    }
                    SectionType.ASK -> {
                        var index = getIndexOfSectionByType(SectionType.TICKER, sections)
                        if (index in sections.indices) {
                            sections.add(index + 1, section)
                        } else {
                            index = getIndexOfSectionByType(SectionType.HEADER, sections)
                            if (index in sections.indices) {
                                sections.add(index + 1, section)
                            } else {
                                sections.add(0, section)
                            }
                        }
                    }
                    SectionType.BID -> sections.add(section)
                }
            }
        }
        _sections.value = sections

        if (section.type != SectionType.HEADER)
            _showLoader.value = false
    }

    private fun getIndexOfSectionByType(type: SectionType, sections: List<DetailSectionItem>): Int {
        return sections.indexOfFirst {
            it.type == type
        }
    }

    fun clean() {
        cryptoRepository.clear()
    }
}