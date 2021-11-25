package com.jbc7ag.cryptso.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: String
)
