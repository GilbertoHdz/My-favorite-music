package com.ghg.favmusicapp.presentation.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Class to be used when emitting events in LiveData that should only be handled only once.
 * Not marking a value as consumed will trigger processing the next time the view is recreated.
 */
data class Event<T>(
  val value: T
) {

  @Suppress("DataClassShouldBeImmutable")
  private var wasConsumed = false

  @MainThread
  fun consume(consumer: (T) -> Unit) {
    if (!wasConsumed) {
      wasConsumed = true
      consumer(value)
    }
  }

  @MainThread
  fun consumeValue(): T? =
    if (!wasConsumed) {
      wasConsumed = true
      value
    } else {
      null
    }
}

fun <T : Any> T.toEvent() = Event(this)

fun <T : Any> MutableLiveData<Event<T>>.postEvent(event: T) = this.postValue(event.toEvent())

@MainThread
fun <T : Any> MutableLiveData<Event<T>>.sendEvent(event: T) {
  this.value = event.toEvent()
}

@MainThread
fun <T> LiveData<Event<T>>.observeEvent(
  owner: LifecycleOwner,
  onChanged: (T) -> Unit
): Observer<Event<T>> {
  val wrappedObserver = Observer<Event<T>> { t -> t.consume(onChanged) }
  observe(owner, wrappedObserver)
  return wrappedObserver
}
