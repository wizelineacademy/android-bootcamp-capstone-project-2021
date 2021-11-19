package com.esaudev.wizeline.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.esaudev.wizeline.data.sources.FakeBitsoErrorRepository
import com.esaudev.wizeline.data.sources.FakeBitsoSuccessRepository
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    private lateinit var listViewModel: ListViewModel
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
    fun `getAvailableBooks sets a list when the DataState is SUCCESS`() {
        // Given
        listViewModel = ListViewModel(fakeBitsoSuccessRepository)
        val books = DataState.Success(
            listOf (
                AvailableBook(
                    book = "btc_mxn",
                    minimumAmount = ".003",
                    maximumAmount = "1000.00",
                    minimumPrice = "100.00",
                    maximumPrice = "1000000.00",
                    minimumValue = "25.00",
                    maximumValue = "1000000.00",
                    icon = "https://cryptoicon-api.vercel.app/api/icon/btc"
                )
            )
        )

        // When
        listViewModel.getAvailableBooks()
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(books, listViewModel.getAvailableBooksEvent.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getAvailableBooks sets an error code when the DataState is ERROR`() {
        // Given
        listViewModel = ListViewModel(fakeBitsoErrorRepository)
        val error = DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)

        // When
        listViewModel.getAvailableBooks()
        dispatcher.advanceUntilIdle()

        // Then
        assertEquals(error, listViewModel.getAvailableBooksEvent.value)
    }
}