package dev.ricsarabia.cryptochallenge.data.repos

import com.google.gson.Gson
import dev.ricsarabia.cryptochallenge.data.db.AppDatabase
import dev.ricsarabia.cryptochallenge.data.db.BookDao
import dev.ricsarabia.cryptochallenge.data.db.BookOrderDao
import dev.ricsarabia.cryptochallenge.data.db.BookPricesDao
import dev.ricsarabia.cryptochallenge.data.services.AvailableBooksResponse
import dev.ricsarabia.cryptochallenge.data.services.BitsoService
import dev.ricsarabia.cryptochallenge.data.services.OrderBookResponse
import dev.ricsarabia.cryptochallenge.data.services.TickerResponse
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Ricardo Sarabia on 2021/11/29.
 */
class BitsoRepoTest {
    private lateinit var repository: BitsoRepo

    @Mock
    lateinit var bitsoService: BitsoService

    @Mock
    private lateinit var database: AppDatabase

    @Mock
    private lateinit var bookDao: BookDao

    @Mock
    private lateinit var bookPricesDao: BookPricesDao

    @Mock
    private lateinit var bookOrderDao: BookOrderDao

    private val httpException = HttpException(
        Response.error<Any>(
            500,
            "Test Server Error".toResponseBody("text/plain".toMediaTypeOrNull())
        )
    )

    @Before
    fun setup() {
        bookDao = mock()
        bookPricesDao = mock()
        bookOrderDao = mock()
        database = mock()
        bitsoService = mock()
        repository = BitsoRepo(database, bitsoService)
    }

    @Test
    fun whenGetAvailableBooksSuccess_updateBooksReturnsTrue() = runBlockingTest {
        // Given
        val responseSample =
            ClassLoader.getSystemResource("availableBooksResponseSample.json").readText()
        val successResponse = Gson().fromJson(responseSample, AvailableBooksResponse::class.java)

        whenever(bitsoService.getAvailableBooks()) doReturn successResponse
        whenever(database.bookDao()) doReturn bookDao

        // When
        val booksUpdated = repository.updateBooks()

        // Then
        assertTrue(booksUpdated)
    }

    @Test
    fun whenGetAvailableBooksFails_updateBooksReturnsFalse() = runBlockingTest {
        // Given
        whenever(bitsoService.getAvailableBooks()) doThrow httpException
        whenever(database.bookDao()) doReturn bookDao

        // When
        val booksUpdated = repository.updateBooks()

        // Then
        assertFalse(booksUpdated)
    }

    @Test
    fun updateBookPricesReturnsTrue_whenGetTickerSuccess() = runBlockingTest {
        // Given
        val sampleBook = "btc_mxn"
        val responseSample =
            ClassLoader.getSystemResource("tickerResponseSample.json").readText()
        val successResponse = Gson().fromJson(responseSample, TickerResponse::class.java)
        whenever(bitsoService.getTicker(sampleBook)) doReturn successResponse
        whenever(database.bookPricesDao()) doReturn bookPricesDao

        // When
        val booksUpdated = repository.updateBookPrices(sampleBook)

        // Then
        assertTrue(booksUpdated)
    }

    @Test
    fun updateBookPricesReturnsFalse_whenUpdateBookPricesFails() = runBlockingTest {
        // Given
        val sampleBook = "btc_mxn"
        whenever(bitsoService.getTicker(sampleBook)) doThrow httpException

        // When
        val pricesUpdated = repository.updateBookPrices(sampleBook)

        // Then
        assertFalse(pricesUpdated)
    }

    @Test
    fun updateBookOrdersReturnsTrue_whenGetOrderBookSuccess() = runBlockingTest {
        // Given
        val book = "btc_mxn"
        val responseSample =
            ClassLoader.getSystemResource("orderBookResponseSample.json").readText()
        val successResponse = Gson().fromJson(responseSample, OrderBookResponse::class.java)
        whenever(bitsoService.getOrderBook(book)) doReturn successResponse
        whenever(database.bookOrderDao()) doReturn bookOrderDao

        // When
        val bookOrdersUpdated = repository.updateBookOrders(book)

        // Then
        assertTrue(bookOrdersUpdated)
    }

    @Test
    fun updateBookOrdersReturnsTrue_whenGetOrderBookFails() = runBlockingTest {
        // Given
        val book = "btc_mxn"
        whenever(bitsoService.getOrderBook(book)) doThrow httpException

        // When
        val bookOrdersUpdated = repository.updateBookOrders(book)

        // Then
        assertFalse(bookOrdersUpdated)
    }
}
