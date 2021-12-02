package com.alexbar10.cryptotrack.networking.repo

import com.alexbar10.cryptotrack.domain.CryptocurrenciesListResponse
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.domain.Order
import com.alexbar10.cryptotrack.domain.OrderDetailItem
import com.alexbar10.cryptotrack.domain.OrderResponse
import com.alexbar10.cryptotrack.domain.Ticker
import com.alexbar10.cryptotrack.domain.TickerResponse
import com.alexbar10.cryptotrack.networking.services.CryptocurrenciesServices
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CryptocurrenciesRepoTest {

    private lateinit var cryptoRepo: CryptocurrenciesRepo

    @Mock
    private lateinit var cryptoServices: CryptocurrenciesServices

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
    private val tickerForCrypto = Ticker(
        100300.00,
        100200.00,
        93406.40,
        "eth_mxn"
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
    private val cryptoApiResponse = CryptocurrenciesListResponse(
        true,
        cryptoList,
        null
    )
    private val ticketApiResponse = TickerResponse(
        true,
        tickerForCrypto,
        null
    )
    private val orderApiResponse = OrderResponse(
        true,
        order
    )

    @Before
    fun setUp() {
        cryptoServices = mock()
        cryptoRepo = CryptocurrenciesRepo(cryptoServices)
    }

    @Test fun `data from getCryptocurrenciesAvailable is not null`() {
        runBlocking {
            // given
            whenever(cryptoServices.getCryptocurrenciesAvailable()) doReturn cryptoApiResponse

            // when
            val cryptosResponse = cryptoServices.getCryptocurrenciesAvailable()

            // then
            val cryptoList = cryptoApiResponse.payload
            assertNotNull(cryptoList)
        }
    }

    @Test fun `data from getCryptocurrenciesAvailable is not empty`() {
        runBlocking {
            // given
            whenever(cryptoServices.getCryptocurrenciesAvailable()) doReturn cryptoApiResponse

            // when
            val cryptosResponse = cryptoServices.getCryptocurrenciesAvailable()

            // then
            val cryptoList = cryptoApiResponse.payload
            assertTrue(cryptoList!!.isNotEmpty())
        }
    }

    @Test fun `data from getTicketFor(crypto) is not null`() {
        runBlocking {
            // given
            whenever(cryptoServices.getTickerFor("eth_mxn")) doReturn ticketApiResponse

            // when
            val tickerResponse = cryptoServices.getTickerFor("eth_mxn")

            // then
            val ticker = tickerResponse.payload
            assertNotNull(ticker)
        }
    }

    @Test fun `data from getTicketFor(crypto) is not empty`() {
        runBlocking {
            // given
            whenever(cryptoServices.getTickerFor("eth_mxn")) doReturn ticketApiResponse

            // when
            val tickerResponse = cryptoServices.getTickerFor("eth_mxn")

            // then
            val ticker = tickerResponse.payload
            assertTrue(
                ticker!!.book.isNotEmpty() &&
                    ticker!!.low > 0 &&
                    ticker!!.high > 0 &&
                    ticker!!.last > 0
            )
        }
    }

    @Test fun `data from getOrderFor(crypto) is not null`() {
        runBlocking {
            // given
            whenever(cryptoServices.getOrderFor("eth_mxn")) doReturn orderApiResponse

            // when
            val orderResponse = cryptoServices.getOrderFor("eth_mxn")

            // then
            val order = orderResponse.payload
            assertNotNull(order)
        }
    }
}
