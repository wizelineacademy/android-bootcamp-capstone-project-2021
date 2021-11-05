package dev.ricsarabia.cryptochallenge.domain

sealed class AvailableBooks {
    class Books(val books: List<Book>): AvailableBooks()
    class Error(val message: String): AvailableBooks()
}
