package dev.ricsarabia.cryptochallenge.domain

sealed class BooksData {
    class Data(val books: List<Book>) : BooksData()
    class Error(val message: String) : BooksData()
}
