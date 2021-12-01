package com.jbc7ag.cryptso.util

import com.jbc7ag.cryptso.data.model.Book
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExtensionsKtTest {


    @Test
    fun `Given an empty String, whe we get the market format, we  should get an empty string`() {

        //Given: I have an empty string
        val emptyString = ""

        //When
        val marketFormat = emptyString.getmarketFormat()
        val currencyCode = emptyString.getCurrencyCode()
        val currencyCodeFilter = emptyString.getCurrencyCodeFilter()
        val formatCurrency = emptyString.formatCurrency()

        //Then
        assertThat(marketFormat, `is`(emptyString))
        assertThat(currencyCode, `is`(emptyString))
        assertThat(currencyCodeFilter , `is`(emptyString))
        assertThat(formatCurrency, `is`(emptyString))
    }

    @Test
    fun `Given an String, when we get the market format, we  should get the correct format`() {
        //Given
        val data = "BTC_MXN"

        //When
        val marketFormat = data.getmarketFormat()

        //Then
        assertThat(marketFormat.getmarketFormat(), `is`("BTC/MXN"))
    }

    @Test
    fun `Given an String, when we get the currency code, we should get the correct currency code`() {
        //Given
        val data = "DAI_MXN"

        //When
        val currencyCode  = data.getCurrencyCode()

        //Then
        assertThat(currencyCode, `is`("DAI"))
    }

    @Test
    fun `Given an String, when we get the currency code for filters, we should get the correct currency code`() {
        //Given
        val data = "LIT_BTC"

        //When
        val currencyCodeFilter  = data.getCurrencyCodeFilter()

        //Then
        assertThat(currencyCodeFilter, `is`("BTC"))
    }

    @Test
    fun `Given an String, when we get the format for currencies, we should get a money format code`() {
        //Given
        val money = "12432.0"

        //When
        val currencyCodeFilter  = money.formatCurrency()

        //Then
        assertThat(currencyCodeFilter, `is`("$12,432"))
    }

    @Test
    fun `Given a list of available currencies, when we get only the filters, we should get a list of filters that matches with the one we are looking `() {
        //Given
        val listBooks = getListBooks()

        //When
        val filterBTC  = listBooks.getFilterList("btc").filter { it.selected }.size
        val filterMXN  = listBooks.getFilterList("mxn").filter { it.selected }.size
        val filterETH  = listBooks.getFilterList("eth").filter { it.selected }.size

        //Then
        assertThat(filterBTC, `is`(1))
        assertThat(filterMXN, `is`(1))
        assertThat(filterETH, `is`(0))
    }

    // MockData
    private fun getListBooks(): List<Book> {
        val books = mutableListOf<Book>()
        books.add(Book(1, "btc", "btc_mxn", "1", "1", "1", "1", "1", "1"))
        books.add(Book(1, "rxp", "xrp_btc", "1", "1", "1", "1", "1", "1"))
        books.add(Book(1, "lit", "lit_mxn", "1", "1", "1", "1", "1", "1"))
        books.add(Book(1, "eth", "eth_lit", "1", "1", "1", "1", "1", "1"))
        books.add(Book(1, "uni", "uni_mxn", "1", "1", "1", "1", "1", "1"))
        return books
    }
}
