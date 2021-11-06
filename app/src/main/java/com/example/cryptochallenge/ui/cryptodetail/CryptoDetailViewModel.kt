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

/**
 * ViewModel for Cryptocurrency detail
 */
class CryptoDetailViewModel : ViewModel() {
    /**
     * Property to handle [CryptoRepository]
     */
    private val cryptoRepository = CryptoRepository()

    /**
     * LiveData for load sections
     */
    private val _sections = SingleLiveEvent<MutableList<DetailSectionItem>>()
    val sections: LiveData<MutableList<DetailSectionItem>> get() = _sections

    /**
     * LiveData for show or hide loader
     */
    private val _showLoader = SingleLiveEvent<Boolean>()
    val showLoader: LiveData<Boolean> get() = _showLoader

    /**
     * Get ticker information of a specific book
     *
     * @param bookName Book name
     * @return [LiveData] with webservice response
     */
    fun getTicker(bookName: String): LiveData<Payload?> {
        return GetTicker(cryptoRepository).execute(bookName)
    }

    /**
     * Get Order book information of a specific book
     *
     * @param bookName Book name
     * @return [LiveData] with webservice response
     */
    fun getOrderBook(bookName: String): LiveData<com.example.cryptochallenge.domain.orderbook.Payload?> {
        return GetOrderBook(cryptoRepository).execute(bookName)
    }

    /**
     * Process section information to show it
     *
     * @param item Section information
     */
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

    /**
     * Add section information to section list
     *
     * @param section Section information
     */
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

    /**
     * Get index of a section by section type
     *
     * @param type Section type
     * @param sections Section list
     * @return [Int] that represent section index
     */
    private fun getIndexOfSectionByType(type: SectionType, sections: List<DetailSectionItem>) =
        sections.indexOfFirst {
            it.type == type
        }

    /**
     * Clean disposable property
     */
    fun clean() {
        cryptoRepository.clear()
    }
}