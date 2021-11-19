package com.example.bootcampproject.ui.availablebooks



import com.example.bootcampproject.data.repo.AvailableBooksRepo
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Inject


class AvailablebooksViewModelTest {

    @InjectMocks
    private lateinit var availablebooksViewModel: AvailablebooksViewModel
    @Mock
    private lateinit var availableBooksRepo: AvailableBooksRepo
    @Before
    fun setUp() {
        availableBooksRepo = Mockito.mock(AvailableBooksRepo::class.java)
        availablebooksViewModel=AvailablebooksViewModel(availableBooksRepo)

    }
    @Test
    fun `t`()  {
        //given
        availablebooksViewModel.getAvailableBooks("btc",true)
        //when
        val list=availablebooksViewModel.books
        //val response = bitsoServices.getOrderBook("btc_mxn")
        //Then
        list.value?.let { assertTrue(it.isEmpty()) }
    }
}