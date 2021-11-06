package com.kcruz.cryptochallenge.framework.response

import com.kcruz.cryptochallenge.domain.Book

class BookResponse(
    val success: Boolean,
    val payload: Book
)