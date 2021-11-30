package com.esaudev.wizeline.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.esaudev.wizeline.data.sources.FakeBitsoErrorRepository
import com.esaudev.wizeline.data.sources.FakeBitsoSuccessRepository
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.utils.Constants.NETWORK_UNKNOWN_ERROR
import com.esaudev.wizeline.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var fakeBitsoSuccessRepository: FakeBitsoSuccessRepository
    private lateinit var fakeBitsoErrorRepository: FakeBitsoErrorRepository
    @ExperimentalCoroutinesApi val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)

        fakeBitsoSuccessRepository = FakeBitsoSuccessRepository()
        fakeBitsoErrorRepository = FakeBitsoErrorRepository()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getOrderBooks sets a OrderBook when the DataState is SUCCESS`() {
        // Given
        detailViewModel = DetailViewModel(fakeBitsoSuccessRepository)
        val orderBook = DataState.Success(
            OrderBook(
                asks = listOf(),
                bids = listOf(),
                sequence = "sequence",
                updated_at = "updated_at"
            )
        )

        // When
        detailViewModel.getOrderBooks("book")
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(orderBook, detailViewModel.getOrderBookState.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getOrderBooks sets an error code when the DataState is ERROR`() {
        // Given
        detailViewModel = DetailViewModel(fakeBitsoErrorRepository)
        val orderBook = DataState.Error(NETWORK_UNKNOWN_ERROR)

        // When
        detailViewModel.getOrderBooks("book")
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(orderBook, detailViewModel.getOrderBookState.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getTickerFromBook sets an ticker when the DataState is SUCCESS`() {
        // Given
        detailViewModel = DetailViewModel(fakeBitsoSuccessRepository)
        val ticker = DataState.Success(
            Ticker(
                ask = "ask",
                bid = "bid",
                book = "book",
                created_at = "created_at",
                high = "high",
                last = "last",
                low = "low",
                volume = "volume",
                vwap = "vwap"
            )
        )

        // When
        detailViewModel.getTickerFromBook("book")
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(ticker, detailViewModel.getTickerState.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getTickerFromBook sets an ticker when the DataState is ERROR`() {
        // Given
        detailViewModel = DetailViewModel(fakeBitsoErrorRepository)
        val error = DataState.Error(NETWORK_UNKNOWN_ERROR)

        // When
        detailViewModel.getTickerFromBook("book")
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(error, detailViewModel.getTickerState.value)
    }
}
