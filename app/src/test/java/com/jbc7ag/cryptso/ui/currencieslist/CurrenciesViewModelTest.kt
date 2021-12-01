package com.jbc7ag.cryptso.ui.currencieslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.data.repository.CurrencyRepository
import com.jbc7ag.cryptso.data.room.dao.BooksDao
import com.jbc7ag.cryptso.data.room.dao.CoinListDao
import com.jbc7ag.cryptso.data.room.dao.OrderDao
import com.jbc7ag.cryptso.data.room.dao.TickerDao
import com.jbc7ag.cryptso.data.services.CurrencyApi
import com.jbc7ag.cryptso.util.Resource
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
class CurrenciesViewModelTest {

    private lateinit var currenciesViewModel: CurrenciesViewModel

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
        currenciesViewModel = CurrenciesViewModel(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
    @Test
    fun `call the method downloadBooks to get the response saved`() = runBlockingTest {
        //GIVEN
        val resource : Resource<List<Book>> = Resource.Success(getBooks())
        whenever(repository.downloadAvailableBooks()).doReturn(resource)
        currenciesViewModel.dispatcher = dispatcher

        //WHEN
        currenciesViewModel.downloadBooks()

        //THEN
        val result = currenciesViewModel.loading.value
        assertThat(result, `is`(false))
    }

    @Test
    fun `call the method downloadBooks with an error response`() = runBlockingTest {
        //GIVEN
        val resource : Resource<List<Book>> = Resource.Error("")
        whenever(repository.downloadAvailableBooks()).doReturn(resource)
        currenciesViewModel.dispatcher = dispatcher

        //WHEN
        currenciesViewModel.downloadBooks()

        //THEN
        val loading = currenciesViewModel.loading.value
        val error = currenciesViewModel.error.value
        assertThat(loading, `is`(false))
        assertThat(error.isNullOrEmpty(), `is`(false))


    }

    @Test
    fun `call the method getBooks to get the list of saved books`() = runBlockingTest {
        //GIVEN
        whenever(repository.getBooks()).doReturn(getBooks())
        currenciesViewModel.dispatcher = dispatcher

        //WHEN
        currenciesViewModel.getBooks()

        //THEN
        val expectedList = getBooks()
        val result = currenciesViewModel.availableBooks.value
        assertThat(expectedList, `is`(result))
    }

    //Mock data
    private fun getBooks(): List<Book> = listOf<Book>(
        Book(1, "test1", "test1", "1", "1", "1", "1", "1", "1"),
        Book(2, "test2", "test2", "1", "1", "1", "1", "1", "1"),
        Book(3, "test3", "test3", "1", "1", "1", "1", "1", "1")
    )
}