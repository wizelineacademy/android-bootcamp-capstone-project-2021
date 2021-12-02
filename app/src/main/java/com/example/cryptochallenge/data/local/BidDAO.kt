package com.example.cryptochallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptochallenge.data.model.Bid

@Dao
interface BidDAO {
    @Query("SELECT * FROM bids WHERE bookName=:bookName")
    fun getAllBidsByBookName(bookName: String): List<Bid>

    @Insert
    fun insertBid(bid: Bid)

    @Query("DELETE FROM bids WHERE bookName=:bookName")
    fun deleteBidsByBookName(bookName: String)
}