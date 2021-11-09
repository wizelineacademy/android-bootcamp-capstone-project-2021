package com.serranoie.data.crypteck.repository.book

import androidx.annotation.WorkerThread
import com.serranoie.crypteck.domain.repositories.BookRepository
import com.serranoie.crypteck.domain.usecases.UseCaseResult
import com.serranoie.data.crypteck.errors.ErrorCodes
import com.serranoie.data.crypteck.networking.BitsoApi
import java.lang.Exception
import javax.inject.Inject

class RemoteBookRepositoryImpl @Inject constructor(private val bitsoApi: BitsoApi): BookRepository {

    @WorkerThread
    override fun getAvailableBooks(): UseCaseResult{
       try {
          val response = bitsoApi.getAvailableBooks().execute()

          if(response.isSuccessful) {
              val bookDto = response.body()
                  ?: return UseCaseResult.Error(ErrorCodes.OK_BUT_INFO_NOT_AVAILABLE)

              return UseCaseResult.Success(bookDto)
          } else {
              // We return here the response code directly to know the HTTP code from the Endpoint
              return UseCaseResult.Error(response.code())
          }
       } catch (e: Exception) {
           return UseCaseResult.Error(ErrorCodes.EXCEPTION_ON_REQUEST, e)
       }
    }

    @WorkerThread
    override fun getOpenOrdersBook(book: String): UseCaseResult {
       try{
            val response = bitsoApi.getOpenOrdersBook(book).execute()

           if(response.isSuccessful) {
              val bookDto = response.body()
                  ?: return UseCaseResult.Error(ErrorCodes.OK_BUT_INFO_NOT_AVAILABLE)

               return UseCaseResult.Success(bookDto)
           } else {
              return UseCaseResult.Error(response.code())
           }
       }catch (e: Exception) {
            return UseCaseResult.Error(ErrorCodes.EXCEPTION_ON_REQUEST, e)
       }
    }
}