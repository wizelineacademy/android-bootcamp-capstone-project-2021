package com.alexbar10.cryptotrack.ui.cryptoAvailable

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexbar10.cryptotrack.database.CryptoDao
import com.alexbar10.cryptotrack.database.repo.CryptoDBRepo
import com.alexbar10.cryptotrack.domain.CryptocurrenciesListResponse
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.ErrorResponse
import com.alexbar10.cryptotrack.domain.Ticker
import com.alexbar10.cryptotrack.networking.repo.CryptocurrenciesRepo
import com.alexbar10.cryptotrack.networking.services.CryptocurrenciesServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CryptocurrenciesAvailableViewModelTest {

    private lateinit var cryptosAvailableViewModel: CryptocurrenciesAvailableViewModel
    @ExperimentalCoroutinesApi val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var cryptoApiRepo: CryptocurrenciesRepo

    @InjectMocks
    private lateinit var cryptoDBRepo: CryptoDBRepo

    @Mock
    private lateinit var cryptoServices: CryptocurrenciesServices

    @Mock
    private lateinit var cryptoDao: CryptoDao

    private val cryptoList = listOf(
        Cryptocurrency(
            "eth_mxn",
            Ticker(
                100300.00,
                100200.00,
                93406.40,
                "eth_mxn"
            )
        ),
        Cryptocurrency(
            "xrp_mxn ",
            Ticker(
                22.0000,
                21.9011,
                21.0821,
                "xrp_mxn "
            )
        ),
        Cryptocurrency(
            "xrp_btc",
            Ticker(
                0.00001766,
                0.00001678,
                0.00001754,
                "xrp_btc"
            )
        ),
    )
    private val cryptoApiResponse = CryptocurrenciesListResponse(
        true,
        cryptoList,
        null
    )
    private val cryptoApiResponseError = CryptocurrenciesListResponse(
        false,
        null,
        ErrorResponse(
            "404",
            "Not found"
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        cryptoServices = mock()
        cryptoDao = mock()

        cryptoApiRepo = CryptocurrenciesRepo(cryptoServices)
        cryptoDBRepo = CryptoDBRepo(cryptoDao)

        cryptosAvailableViewModel = CryptocurrenciesAvailableViewModel(
            cryptoApiRepo,
            cryptoDBRepo
        )
    }

    @ExperimentalCoroutinesApi
    @Test fun `getCryptocurrenciesAvailable returns data`() {
        runBlocking {
            // Given
            whenever(cryptoServices.getCryptocurrenciesAvailable()) doReturn cryptoApiResponse

            // When
            cryptosAvailableViewModel.getCryptocurrenciesAvailable()
            dispatcher.advanceUntilIdle()

            // Then
            assertEquals(cryptoList, cryptosAvailableViewModel.cryptoAvailableDetailsLiveData.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test fun `showCryptocurrenciesFor returns null for cryptos when error happens in API`() {
        runBlocking {
            // Given
            whenever(cryptoServices.getCryptocurrenciesAvailable()) doReturn cryptoApiResponseError

            // When
            cryptosAvailableViewModel.getCryptocurrenciesAvailable()
            dispatcher.advanceUntilIdle()

            // Then
            assertNull(cryptosAvailableViewModel.cryptoAvailableDetailsLiveData.value)
        }
    }
}
