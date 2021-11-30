package com.alexbar10.cryptotrack.ui.cryptoDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexbar10.cryptotrack.database.CryptoDao
import com.alexbar10.cryptotrack.database.repo.CryptoDBRepo
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Order
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.Ticker
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
import com.alexbar10.cryptotrack.networking.repo.CryptosRxRepo
import com.alexbar10.cryptotrack.networking.services.CryptocurrenciesServices
import com.alexbar10.cryptotrack.networking.services.CryptosRxServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CryptocurrencyDetailsViewModelTest {

    private lateinit var cryptocurrencyDetailsViewModel: CryptocurrencyDetailsViewModel
    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var cryptoApiRepo: CryptocurrenciesRepo

    @InjectMocks
    private lateinit var cryptoRxRepo: CryptosRxRepo

    @InjectMocks
    private lateinit var cryptoDBRepo: CryptoDBRepo

    @Mock
    private lateinit var cryptoServices: CryptocurrenciesServices

    @Mock
    private lateinit var cryptoRxServices: CryptosRxServices

    @Mock
    private lateinit var cryptoDao: CryptoDao

    private val crypto = Cryptocurrency(
        "eth_mxn",
        Ticker(
            100300.00,
            100200.00,
            93406.40,
            "eth_mxn"
        )
    )
    private val order = Order(
        "2021-11-30T19:01:57+00:00",
        listOf(
            OrderDetailItem(
                "eth_mxn",
                1248000.70,
                0.01752868
            )
        ),
        listOf(
            OrderDetailItem(
                "eth_mxn",
                1253696.99,
                0.30520000
            )
        )
    )
    private val orderApiResponse = OrderResponse(
        true,
        order
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        cryptoServices = mock()
        cryptoRxServices = mock()
        cryptoDao = mock()

        cryptoApiRepo = CryptocurrenciesRepo(cryptoServices)
        cryptoRxRepo = CryptosRxRepo(cryptoRxServices)
        cryptoDBRepo = CryptoDBRepo(cryptoDao)

        cryptocurrencyDetailsViewModel = CryptocurrencyDetailsViewModel(
            cryptoApiRepo,
            cryptoRxRepo,
            cryptoDBRepo
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getOrderFor(crypto) returns data`() {
        runBlocking {
            // Given
            whenever(cryptoServices.getOrderFor(crypto.book)) doReturn orderApiResponse

            // When
            cryptocurrencyDetailsViewModel.getOrderFor(crypto)
            dispatcher.advanceUntilIdle()

            // Then
            assertEquals(orderApiResponse, cryptocurrencyDetailsViewModel.cryptocurrencyOrderLiveData.value)
        }
    }
}
