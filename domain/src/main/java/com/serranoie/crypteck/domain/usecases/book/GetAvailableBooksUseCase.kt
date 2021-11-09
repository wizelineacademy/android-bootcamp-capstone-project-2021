package com.serranoie.crypteck.domain.usecases.book

import com.serranoie.crypteck.domain.repositories.BookRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult
import javax.inject.Inject

open class GetAvailableBooksUseCase @Inject constructor(private val bookRepository: BookRepository) {

   operator fun invoke(): UseCaseResult {
      return bookRepository.getAvailableBooks()
   }

}