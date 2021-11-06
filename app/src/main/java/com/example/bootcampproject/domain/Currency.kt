package com.example.bootcampproject.domain


import com.example.bootcampproject.data.mock.Payload

data class Currency (
    val code: String,
    val name: String,
    val imageUrl: String,
    val books: MutableList<Payload> = mutableListOf<Payload>(),
)