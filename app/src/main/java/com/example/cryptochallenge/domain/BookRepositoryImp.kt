package com.example.cryptochallenge.domain

import com.example.cryptochallenge.common.toBook
import com.example.cryptochallenge.data.local.AskDAO
import com.example.cryptochallenge.data.local.BidDAO
import com.example.cryptochallenge.data.local.BookDAO
import com.example.cryptochallenge.data.model.*
import com.example.cryptochallenge.data.remote.BookDataSource
import com.example.cryptochallenge.util.InternetConnection.isNetworkAvailable
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImp @Inject constructor(
    private val dataSource: BookDataSource,
    private val localBookSource: BookDAO,
    private val localAskSource: AskDAO,
    private val localBidSource: BidDAO,
    private val coroutineDispatcher: CoroutineDispatcher,
    ): BookRepository{

    override fun getBooksWithRx(): Observable<GetBooks> {
        return dataSource.getBooksWithRx()
    }

    override suspend fun getBooks(): List<Book> = withContext(coroutineDispatcher) {
        if (isNetworkAvailable()) {
            localBookSource.truncateTable()
            dataSource.getBooks().payload.map { it.toBook() }.onEach {
                localBookSource.insertBook(it)
            }
        }
        localBookSource.getAllBooks()
    }

    override suspend fun getBookDetails(bookName: String): Book = withContext(coroutineDispatcher){
        if (isNetworkAvailable()) {
            val bookWithDetails = dataSource.getBookDetails(bookName).payload
            localBookSource.updateBookByName(bookWithDetails.last, bookWithDetails.high, bookWithDetails.low, bookWithDetails.name)
        }
        localBookSource.getBookByName(bookName)
    }

    override suspend fun getBookOrders(bookName: String): BookOrders = withContext(coroutineDispatcher){
        if (isNetworkAvailable()) {
            val bookOrders = dataSource.getBookOrders(bookName).payload
            localAskSource.deleteAsksByBookName(bookName)
            bookOrders.asks.onEach {
                it.bookName = bookName
                localAskSource.insertAsk(it)
            }

            localBidSource.deleteBidsByBookName(bookName)
            bookOrders.bids.onEach {
                it.bookName = bookName
                localBidSource.insertBid(it)
            }
        }

        BookOrders(localAskSource.getAllAsksByBookName(bookName), localBidSource.getAllBidsByBookName(bookName))
    }
}