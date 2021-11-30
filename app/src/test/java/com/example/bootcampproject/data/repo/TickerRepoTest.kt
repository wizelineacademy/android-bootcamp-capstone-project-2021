package com.example.bootcampproject.data.repo

import com.example.bootcampproject.ConnectRetrofitAndRoom
import com.example.bootcampproject.data.local.CurrencyDao
import com.example.bootcampproject.data.local.TickerDao
import com.example.bootcampproject.data.mock.StatusAvailableBooks
import com.example.bootcampproject.data.mock.StatusTicker
import com.example.bootcampproject.data.mock.Ticker
import com.example.bootcampproject.data.services.BitsoServices
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.mock.Calls

class TickerRepoTest {
    @InjectMocks
    private lateinit var tickerRepo: TickerRepo

    @Mock
    private lateinit var bitsoServices: BitsoServices

    @Mock
    private lateinit var provideTicker: TickerDao

    private lateinit var statusTicker: StatusTicker

    private lateinit var response: Response<StatusTicker>

    private lateinit var retrofitInstance: BitsoServices

    @Before
    fun setUp() {
        bitsoServices = Mockito.mock(BitsoServices::class.java)
        provideTicker = Mockito.mock(TickerDao::class.java)

        tickerRepo = TickerRepo(bitsoServices, provideTicker)
        statusTicker = StatusTicker(
            true,
            Ticker(
                id = 1,
                high = 600.0,
                last = 2.0,
                created_at = "demo",
                book = "btc_mxn",
                volume = 3.0,
                vwap = 3.0,
                low = 2.0,
                ask = 45.0,
                bid = 80.0,
                change_24 = 30.0
            )
        )
    }

    @Test
    fun `return empty body from bits services when app is online for ticker but api is offline `() =
        runBlocking {
            // given
            response = Calls.response(
                StatusTicker(
                    true,
                    Ticker(
                        high = 0.0,
                        last = 0.0,
                        created_at = "demo",
                        book = "",
                        volume = 0.0,
                        vwap = 0.0,
                        low = 0.0,
                        ask = 0.0,
                        bid = 0.0,
                        change_24 = 0.0
                    )
                )
            ).execute()
            whenever(bitsoServices.getTicker("btc_mxn")).thenReturn(response)
            // when
            val fetchData = tickerRepo.getTicker("btc_mxn", true)
            // Then
            assertNull(fetchData?.id)
        }

    @Test
    fun `check if tickerRepo fetch data when app is online`() = runBlocking {
        // given
        response = Calls.response(
            statusTicker
        ).execute()
        whenever(bitsoServices.getTicker("btc_mxn")).thenReturn(response)
        // when
        val fetchData = tickerRepo.getTicker("btc_mxn", true)
        // Then
        assertNotNull(fetchData)
    }
}