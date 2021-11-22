package com.alexbar10.cryptotrack.database.repo

import com.alexbar10.cryptotrack.database.CryptoDao
import com.alexbar10.cryptotrack.database.entities.CryptoEntity
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CryptoDBRepo @Inject constructor(
    private val cryptoDao: CryptoDao
) {
    fun getLocalCryptos(): Flow<List<CryptoEntity>> = cryptoDao.getAvailableCryptos()

    fun insertLocalCryptos(list: List<Cryptocurrency>) {
        val listEntities = mutableListOf<CryptoEntity>()
        list.forEach {
            listEntities.add(CryptoEntity(it.book, 0.0, 0.0, 0.0))
        }
        cryptoDao.insertCryptos(listEntities.toList())
    }

    fun getLocalCryptoFor(book: String): Flow<CryptoEntity?> = cryptoDao.getCryptoBy(book)

    fun updateLocalCryptoWith(newCrypto: CryptoEntity) = cryptoDao.update(newCrypto)
}