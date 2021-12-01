package com.example.bootcampproject.data.repo

import com.example.bootcampproject.ConnectRetrofitAndRoom
import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.mock.Asks
import com.example.bootcampproject.data.mock.Bids
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.services.BitsoServices
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.mock.Calls

class OrderBookRepoTest : ConnectRetrofitAndRoom() {

    @InjectMocks
    private lateinit var orderBookRepo: OrderBookRepo

    @Mock
    private lateinit var bitsoServices: BitsoServices

    @Mock
    private lateinit var provideOrderBooks: OrderBookDao

    // private lateinit var db: BitsoAppDataBase

    private lateinit var statusOrderBook: StatusOrderBook

    private lateinit var response: Response<StatusOrderBook>

    private lateinit var retrofitInstance: BitsoServices

    @Before
    fun setUp() {

        bitsoServices = mock(BitsoServices::class.java)
        provideOrderBooks = mock(OrderBookDao::class.java)

        // db = createRoomInstance()
        // provideOrderBooks = db.getOrderBooks()

        retrofitInstance = createRetrofitInstance()
        orderBookRepo = OrderBookRepo(bitsoServices, provideOrderBooks)

        statusOrderBook = StatusOrderBook(
            true,
            OrderBook(
                updated_at = "1", sequence = 122,
                bids = listOf<Bids>(
                    Bids("btc_mxn", 200.0, 300.0)
                ),
                asks = listOf<Asks>(
                    Asks("btc_mxn", 200.0, 300.0)
                )
            )
        )
    }

    @Test
    fun `return empty body from bits services when code parameter does not exist but is online`() =
        runBlocking {
            // given
            response = Calls.response(
                StatusOrderBook(
                    true,
                    OrderBook(
                        updated_at = "",
                        sequence = 0,
                        bids = listOf<Bids>(),
                        asks = listOf<Asks>()
                    )
                )
            ).execute()
            whenever(bitsoServices.getOrderBook("")).thenReturn(response)
            // when
            val fetchData = orderBookRepo.getOrderBooks("", true)
            // Then
            assertNull(fetchData?.id)
        }

    @Test
    fun `check if orderBookRepo fetch data when app is online`() = runBlocking {
        // given
        response = Calls.response(
            statusOrderBook
        ).execute()
        whenever(bitsoServices.getOrderBook("btc_mxn")).thenReturn(response)
        // when
        val fetchData = orderBookRepo.getOrderBooks("btc_mxn", true)
        // Then
        assertNotNull(fetchData)
    }

    @Test
    fun `Check if API delivers order book info correctly`() = runBlocking {
        // given
        bitsoServices = retrofitInstance
        orderBookRepo = OrderBookRepo(bitsoServices, provideOrderBooks)
        // when
        val fetchData = orderBookRepo.getOrderBooks("btc_mxn", true)
        // then
        assertTrue(fetchData != null)
    }

    /* @Test
    @Throws(Exception::class)
    fun `fetch data from database room when app is offline`() = runBlocking {
        provideOrderBooks.insert(statusOrderBook.payload)
        val fetchData = orderBookRepo.getOrderBooks("btc_mxn", false)
        assertNotNull(fetchData)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }*/
}
