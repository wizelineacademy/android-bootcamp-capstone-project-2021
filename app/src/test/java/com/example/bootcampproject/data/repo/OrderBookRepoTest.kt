package com.example.bootcampproject.data.repo

import com.example.bootcampproject.data.local.OrderBookDao
import com.example.bootcampproject.data.mock.Asks
import com.example.bootcampproject.data.mock.Bids
import com.example.bootcampproject.data.mock.OrderBook
import com.example.bootcampproject.data.mock.StatusOrderBook
import com.example.bootcampproject.data.services.BitsoServices
import com.example.bootcampproject.di.NetworkingModule_ProvideActualCurrencyFactory.provideActualCurrency
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.Mockito.calls
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls

class OrderBookRepoTest {
    @InjectMocks
    private lateinit var orderBookRepo: OrderBookRepo
    @Mock
    private lateinit var bitsoServices : BitsoServices
    @Mock
    private lateinit var provideOrderBooks: OrderBookDao

    private lateinit var response : Call<StatusOrderBook>

    @Before fun setUp(){
        bitsoServices= mock(BitsoServices::class.java)
        provideOrderBooks= mock(OrderBookDao::class.java)
        orderBookRepo=OrderBookRepo(bitsoServices,provideOrderBooks)

       /* response=Calls.response(StatusOrderBook(false,
            OrderBook(updated_at="",sequence=0,bids= listOf<Bids>(), asks = listOf<Asks>()) )
        ).execute()*/

    }
    @Test fun `return empty body from bits services when code parameter does not exist but is online`() = runBlocking{
        //given
        response=Calls.response(bitsoServices.getOrderBook("btc_mxn"))
        //when
        whenever(bitsoServices.getOrderBook("btc_mxn")).thenReturn(response.execute())
        val fetchData= response
        //Then
        assertTrue(fetchData==null)
    }
    @Test fun `t`()= runBlocking{
        //given
        //whenever(bitsoServices.getOrderBook("")).thenReturn(response)
        //when

        //Then
    }
}