package com.example.bootcampproject.ui.availablebooks

import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class AvailablebooksViewModelTest {

    @Inject
    private lateinit var availablebooksViewModel: AvailablebooksViewModel

    @Before
    fun setUp() {
    }

    @Test
    fun `t`() {
        // given
        availablebooksViewModel.getAvailableBooks("btc", true)
        // when
        val list = availablebooksViewModel.books.value
        // val response = bitsoServices.getOrderBook("btc_mxn")
        // Then
        list?.let { assertTrue(it.isEmpty()) }
    }
}
