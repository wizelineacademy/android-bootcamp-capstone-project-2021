package com.kcruz.cryptochallenge.framework.response

import com.kcruz.cryptochallenge.domain.Book

//TODO: Add documentation

class BookResponse(
    val success: Boolean,
    val payload: Book
)