package com.example.bootcampproject.data.mock


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class StatusAvailableBooks (

    @Json(name = "success") val success : Boolean,
    @Json(name = "payload") val payload : List<AvailableBook>,
)

@Entity( indices = [Index(value = ["book"], unique = true)])
@JsonClass(generateAdapter = true)
data class AvailableBook (

    @PrimaryKey(autoGenerate = true) val id :Int?=null,
    @Json(name = "book") val book : String,
    @Json(name = "minimum_price") val minimum_price : Double,
    @Json(name = "maximum_price") val maximum_price : Double,
    @Json(name = "minimum_amount") val minimum_amount : Double,
    @Json(name = "maximum_amount") val maximum_amount : Double,
    @Json(name = "minimum_value") val minimum_value : Double,
    @Json(name = "maximum_value") val maximum_value : Double,
    @Json(name = "tick_size") val tick_size : Double,
    @Json(name = "default_chart") val default_chart : String,
    //@Json(name = "fees") val fees : Fees ,
)


@JsonClass(generateAdapter = true)
data class Fees (

    @Json(name ="flat_rate") val flat_rate : FlatRate,
    @Json(name ="structure") val structure : List<Structure>
)


@JsonClass(generateAdapter = true)
data class FlatRate (

    @Json(name ="maker") val maker : Double,
    @Json(name ="taker") val taker : Double,
)

@JsonClass(generateAdapter = true)
data class Structure (

    @Json(name ="volume") val volume : Double,
    @Json(name ="maker") val maker : Double,
    @Json(name ="taker") val taker : Double,
)