package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.mock.Asks
import com.example.bootcampproject.data.mock.Bids
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.services.BitsoServices
import com.squareup.moshi.Moshi
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.Calls
import javax.inject.Inject

private const val CURRENCY_BASE_URL = "https://api.bitso.com/v3/"

class OrderBookRepoTest  {

    @InjectMocks
    private lateinit var orderBookRepo: OrderBookRepo

    @Mock
    private lateinit var bitsoServices: BitsoServices

    @Mock
    private lateinit var provideOrderBooks: OrderBookDao

    private lateinit var response: Response<StatusOrderBook>

    private lateinit var retrofitInstance : BitsoServices

    @Before
    fun setUp() {

        bitsoServices = mock(BitsoServices::class.java)
        provideOrderBooks = mock(OrderBookDao::class.java)

        val moshi = Moshi.Builder().build()
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)
         retrofitInstance = Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(CURRENCY_BASE_URL)
            .build()
            .create(BitsoServices::class.java)

        orderBookRepo = OrderBookRepo(bitsoServices, provideOrderBooks)
    }

    @Test
    fun `return empty body from bits services when code parameter does not exist but is online`() =
        runBlocking {
            //given
            response= Calls.response(StatusOrderBook(true,
                OrderBook(updated_at="",sequence=0,bids= listOf<Bids>(), asks = listOf<Asks>()) )
            ).execute()
            whenever(bitsoServices.getOrderBook("")).thenReturn(response)
            //when
            val fetchData = orderBookRepo.getOrderBooks("",true)
            //Then
            assertTrue(fetchData == null)
        }

    @Test
    fun `check if orderBookRepo fetch data when app is online`() = runBlocking {
        //given
        response= Calls.response(StatusOrderBook(true,
            OrderBook(updated_at="1",sequence=122,bids= listOf<Bids>(
                Bids("btc_mxn",200.0,300.0)), asks = listOf<Asks>(
                Asks("btc_mxn",200.0,300.0))) )
        ).execute()
        whenever(bitsoServices.getOrderBook("btc_mxn")).thenReturn(response)
        //when
        val fetchData = orderBookRepo.getOrderBooks("btc_mxn",true)
        //Then
        if (fetchData != null) {
            assertTrue(fetchData.bids[0].book=="btc_mxn")
        }
    }
    @Test
    fun `Check if API delivers order book info correctly`() = runBlocking {
        //given
        bitsoServices=retrofitInstance
        orderBookRepo = OrderBookRepo(bitsoServices, provideOrderBooks)
        //when
        val fetchData = orderBookRepo.getOrderBooks("btc_mxn",true)
        //Then
        if (fetchData != null) {
            assertTrue(fetchData.bids[0].book=="btc_mxn")
        }
    }

}