package com.jbc7ag.cryptso.ui.currencydetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbc7ag.cryptso.data.model.Bids
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.room.dao.OrderDao
import com.jbc7ag.cryptso.data.room.dao.TickerDao
import com.jbc7ag.cryptso.data.services.CurrencyApi
import com.jbc7ag.cryptso.util.Resource
import com.jbc7ag.cryptso.util.getCurrencyCode
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class CurrencyDetailViewModelTest {

    private lateinit var currencyDetailViewModel: CurrencyDetailViewModel

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

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {

        Dispatchers.setMain(dispatcher)

        service = mock()
        coinListDao = mock()
        booksDao = mock()
        orderDao = mock()
        tickerDao = mock()

        repository = CurrencyRepository(service, coinListDao, booksDao, orderDao, tickerDao)
        currencyDetailViewModel = CurrencyDetailViewModel(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `call the method getBookName to get the name of the book we select`() = runBlockingTest {
        //GIVEN
        val bookCode = "btc_mxn"
        val expected = "bitcoin"
        whenever(repository.getCoinListBySymbol(bookCode.getCurrencyCode())).doReturn(expected)
        currencyDetailViewModel.dispatcher = dispatcher

        //WHEN
        currencyDetailViewModel.getBookName(bookCode)

        //THEN
        val result = currencyDetailViewModel.coinName.value
        assertThat(expected, `is`(result))
    }

    @Test
    fun `call the method getTicker to get the book details`() = runBlockingTest {

        //GIVEN
        val bookCode = "btc_mxn"
        whenever(repository.getTicker(bookCode)).doReturn(getBookDetails())
        currencyDetailViewModel.dispatcher = dispatcher

        //WHEN
        currencyDetailViewModel.getTicker(bookCode)

        //THEN
        val expectedValue = getBookDetails()
        val result = currencyDetailViewModel.bookTicker.value
        assertThat(expectedValue, `is`(result))
    }

    @Test
    fun `call the method getOrder to get the bids and the asks`() = runBlockingTest {

        //GIVEN
        val bookCode = "btc_mxn"
        whenever(repository.getOrder(bookCode)).doReturn(getOrderDetail())
        currencyDetailViewModel.dispatcher = dispatcher

        //WHEN
        currencyDetailViewModel.getOrder(bookCode)

        //THEN
        val expectedValue = getOrderDetail()
        val result = currencyDetailViewModel.orders.value
        assertThat(expectedValue, `is`(result))
    }

    @Test
    fun `call the method downloadTicker to get the ticker data`() = runBlockingTest {
        //GIVEN
        val bookCode = "btc_mxn"
        val resource : Resource<BookDetail> = Resource.Success(getBookDetails())
        whenever(repository.downloadTicker(bookCode)).doReturn(resource)
        currencyDetailViewModel.dispatcher = dispatcher

        //WHEN
        currencyDetailViewModel.downloadTicker(bookCode)

        //THEN
        val result = currencyDetailViewModel.loadingTicker.value
        assertThat(result, `is`(false))
    }

    @Test
    fun `call the method downloadOrder to get the Order details`() = runBlockingTest {
        //GIVEN
        val bookCode = "btc_mxn"
        val resource : Resource<OrderDetail> = Resource.Success(getOrderDetail())
        whenever(repository.downloadOrders(bookCode)).doReturn(resource)
        currencyDetailViewModel.dispatcher = dispatcher

        //WHEN
        currencyDetailViewModel.downloadOrders(bookCode)

        //THEN
        val result = currencyDetailViewModel.loadingOrders.value
        assertThat(result, `is`(false))
    }

    //Mock data
    private fun getBookDetails(): BookDetail = BookDetail(1, "test", "1", "1", "1", "1", "1", "1", "2", "1", "1")

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