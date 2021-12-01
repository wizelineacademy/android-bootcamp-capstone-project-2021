package com.example.bootcampproject.data.repo

import com.example.bootcampproject.ConnectRetrofitAndRoom
import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.services.BitsoServices
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.mock.Calls

class CurrencyRepoTest : ConnectRetrofitAndRoom() {

    @InjectMocks
    private lateinit var currencyRepo: CurrencyRepo

    @Mock
    private lateinit var bitsoServices: BitsoServices

    @Mock
    private lateinit var provideCurrency: CurrencyDao

    private lateinit var statusAvailable: StatusAvailableBooks

    private lateinit var response: Response<StatusAvailableBooks>

    private lateinit var retrofitInstance: BitsoServices

    @Before
    fun setUp() {
        bitsoServices = Mockito.mock(BitsoServices::class.java)
        provideCurrency = Mockito.mock(CurrencyDao::class.java)

        currencyRepo = CurrencyRepo(bitsoServices, provideCurrency)
        statusAvailable = StatusAvailableBooks(
            true,
            listOf(
                AvailableBook(
                    id = 1,
                    book = "btc_mxn",
                    minimum_price = 2.0,
                    maximum_price = 500.0,
                    minimum_amount = 5.0,
                    maximum_amount = 500.0,
                    minimum_value = 2.0,
                    maximum_value = 500.0,
                    tick_size = 5.0,
                    default_chart = "1"
                ),
                AvailableBook(
                    id = 2,
                    book = "btc_eth",
                    minimum_price = 3.0,
                    maximum_price = 200.0,
                    minimum_amount = 3.0,
                    maximum_amount = 200.0,
                    minimum_value = 3.0,
                    maximum_value = 200.0,
                    tick_size = 5.0,
                    default_chart = "2"
                )
            )
        )
    }

    @Test
    fun `return empty body from bits services when app is online for currency but api is offline `() =
        runBlocking {
            // given
            response = Calls.response(
                StatusAvailableBooks(
                    true,
                    emptyList()
                )
            ).execute()
            whenever(bitsoServices.getAvailableBooks()).thenReturn(response)
            // when
            val fetchData = currencyRepo.getCurrencies(true)
            // Then
            assertTrue(fetchData.isEmpty())
        }

    @Test
    fun `check if currencyRepo fetch data when app is online`() = runBlocking {
        // given
        response = Calls.response(
            statusAvailable
        ).execute()
        whenever(bitsoServices.getAvailableBooks()).thenReturn(response)
        // when
        val fetchData = currencyRepo.getCurrencies(true)
        // Then
        assertNotNull(fetchData)
        assertNotNull(fetchData[0].imageUrl)
    }
}
