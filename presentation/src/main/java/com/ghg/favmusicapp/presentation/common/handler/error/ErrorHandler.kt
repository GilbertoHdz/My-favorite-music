package com.ghg.favmusicapp.presentation.common.handler.error

import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@ActivityRetainedScoped
class ErrorHandler @Inject constructor() {

  fun process(
    throwable: Throwable
  ) : ErrorType {
    throwable.printStackTrace()

    return when (throwable) {
      is IllegalStateException -> ErrorType.UNKNOWN
      is HttpException -> {
        return ErrorType.HTTP_EXCEPTION
      }
      else -> {
        return unknownError(throwable)
      }
    }
  }

  private fun unknownError(exception: Throwable): ErrorType {
    return when (exception) {
      is UnknownHostException,
      is ConnectException -> ErrorType.NO_INTERNET_CONNECTION
      else -> ErrorType.UNKNOWN
    }
  }
}