package com.serranoie.crypteck.domain.usecases.book

import com.serranoie.crypteck.domain.repositories.BookRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult

class GetAvailableBooksUseCase(private val bookRepository: BookRepository) {

   operator fun invoke(): UseCaseResult {
      return bookRepository.getAvailableBooks()
   }

}