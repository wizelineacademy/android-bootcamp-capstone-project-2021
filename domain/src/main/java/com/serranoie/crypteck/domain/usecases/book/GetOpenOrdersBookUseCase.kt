package com.serranoie.crypteck.domain.usecases.book

import com.serranoie.crypteck.domain.repositories.BookRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult
import javax.inject.Inject

open class GetOpenOrdersBookUseCase @Inject constructor(private val bookRepository: BookRepository) {

    operator fun invoke(book: String): UseCaseResult {
        return bookRepository.getOpenOrdersBook(book)
    }

}