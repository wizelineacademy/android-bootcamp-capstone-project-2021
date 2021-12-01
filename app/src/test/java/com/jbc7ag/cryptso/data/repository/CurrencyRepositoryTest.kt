package com.jbc7ag.cryptso.data.repository

import com.jbc7ag.cryptso.data.model.*
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.room.dao.OrderDao
import com.jbc7ag.cryptso.data.room.dao.TickerDao
import com.jbc7ag.cryptso.data.services.CurrencyApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class CurrencyRepositoryTest {
    @Mock
    private lateinit var service: CurrencyApi

    @Mock
    private lateinit var coinListDao: CoinListDao

    @Mock
    private lateinit var booksDao: BooksDao

    @Mock
    private lateinit var orderDao: OrderDao

    @Mock
    private lateinit var tickerDao: TickerDao

    @InjectMocks
    private lateinit var repository: CurrencyRepository

    @Before
    fun init() {
        service = mock()
        coinListDao = mock()
        booksDao = mock()
        orderDao = mock()
        tickerDao = mock()

        repository = CurrencyRepository(service, coinListDao, booksDao, orderDao, tickerDao)

    }

    @Test
    fun `call the method downloadAvailableBooks to get the books from the service`() =
        runBlockingTest {
            //GIVEN
            val resource: Response<AvailableBooks> = Response.success(getAvailableBooks())
            whenever(service.getAvailableBooks()).doReturn(resource)

            //WHEN
            val result = repository.downloadAvailableBooks()

            //THEN
            assertThat(result.data?.size, `is`(3))
        }

    @Test
    fun `call the method downloadOrders to get the books from the service`() =
        runBlockingTest {
            //GIVEN
            val book = "btc_mxn"
            val resource: Response<Orders> = Response.success(getOrders())
            whenever(service.getOrder(book)).doReturn(resource)

            //WHEN
            val result = repository.downloadOrders(book)

            //THEN
            assertThat(result.data?.id, `is`(1))
            assertThat(result.data?.asks?.size, `is`(3))
        }

    @Test
    fun `call the method downloadTicker to get the tickers from the service`() =
        runBlockingTest {
            //GIVEN
            val book = "btc_mxn"
            val resource: Response<Ticker> = Response.success(getTicker())
            whenever(service.getTicker(book)).doReturn(resource)

            //WHEN
            val result = repository.downloadTicker(book)

            //THEN
            assertThat(result.data?.id, `is`(1))
        }

    //Mock data

    //Books
    private fun getAvailableBooks(): AvailableBooks =
        AvailableBooks(true, Error("", ""), getBooks())

    private fun getBooks(): List<Book> = listOf<Book>(
        Book(1, "test1", "test1", "1", "1", "1", "1", "1", "1"),
        Book(2, "test2", "test2", "1", "1", "1", "1", "1", "1"),
        Book(3, "test3", "test3", "1", "1", "1", "1", "1", "1")
    )

    //Tickers
    private fun getTicker(): Ticker = Ticker(true, Error("", ""), getBookDetails())
    private fun getBookDetails(): BookDetail = BookDetail(1, "test", "1", "1", "1", "1", "1", "1", "2", "1", "1")


    //Orders
    private fun getOrders(): Orders = Orders(true, Error("", ""), getOrderDetail())
    private fun getOrderDetail(): OrderDetail = OrderDetail(1, "test", "1", "1", getBids(), getArks())

    private fun getBids() = listOf(
        Bids("btc_mx", "1", "1"),
        Bids("btc_mx", "2", "2"),
        Bids("btc_mx", "3", "3")
    )

    private fun getArks() = listOf(
        Bids("btc_mx", "1", "1"),
        Bids("btc_mx", "2", "2"),
        Bids("btc_mx", "3", "3")
    )
}