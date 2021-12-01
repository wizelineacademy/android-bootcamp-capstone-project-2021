package com.jbc7ag.cryptso.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: Error,
    @SerializedName("payload") val payload: OrderDetail
)

@Entity(tableName = "orders")
data class OrderDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var book: String,
    @SerializedName("updated_at") val date: String,
    @SerializedName("sequence") val sequence: String,
    @SerializedName("bids") val bids: List<Bids>,
    @SerializedName("asks") val asks: List<Bids>

)

data class Bids(
    @SerializedName("book") val book: String,
    @SerializedName("price") val price: String,
    @SerializedName("amount") val amount: String
)

class BidstoCollectionTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Bids>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Bids>::class.java).toList()
}
