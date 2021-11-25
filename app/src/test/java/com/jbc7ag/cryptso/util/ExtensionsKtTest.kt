package com.jbc7ag.cryptso.util

import com.jbc7ag.cryptso.data.model.Book
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExtensionsKtTest {

    private val emptyString = ""

    @Test
    fun `Given an empty String, whe we get the market format, we  should get an empty string`() {
        assertThat(emptyString.getmarketFormat(), `is`(emptyString))
        assertThat(emptyString.getCurrencyCode(), `is`(emptyString))
        assertThat(emptyString.getCurrencyCodeFilter(), `is`(emptyString))
        assertThat(emptyString.formatCurrency(), `is`(emptyString))
    }

    @Test
    fun `Given an String, when we get the market format, we  should get the correct format`() {
        assertThat("BTC_MXN".getmarketFormat(), `is`("BTC/MXN"))
    }

    @Test
    fun `Given an String, when we get the currency code, we should get the correct currency code`() {
        assertThat("BTC_MXN".getCurrencyCode(), `is`("BTC"))
        assertThat("BTC_MXN".getCurrencyCode(), not(`is`("MXN")))
    }

    @Test
    fun `Given an String, when we get the currency code for filters, we should get the correct currency code`() {
        assertThat("BTC_MXN".getCurrencyCodeFilter(), `is`("MXN"))
        assertThat("BTC_MXN".getCurrencyCodeFilter(), not(`is`("BTC")))
    }

    @Test
    fun `Given an String, when we get the format for currencies, we should get a money format code`() {
        val money = "12432.0"
        assertThat(money.formatCurrency(), `is`("$12,432"))
    }

    @Test
    fun `Given a list of available currencies, when we get only the filters, we should get a list of filters that matches with the one we are looking `() {
        val listBooks = getListBooks()
        assertThat(listBooks.getFilterList("btc").filter { it.selected }.size, `is`(1))
        assertThat(listBooks.getFilterList("mxn").filter { it.selected }.size, `is`(1))
        assertThat(listBooks.getFilterList("eth").filter { it.selected }.size, `is`(0))
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
