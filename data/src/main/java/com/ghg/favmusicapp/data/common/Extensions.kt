package com.ghg.favmusicapp.data.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

fun <T> callApi(
  dispatcher: CoroutineContext = Dispatchers.IO,
  call: suspend () -> T
): Flow<T> = suspend {
  try {
    call.invoke()
  } catch (t: Throwable) {
    throw t
  }
}.asFlow().flowOn(dispatcher)