package com.dokabalazs.reaktive.observable

import java.lang.Error

open class Observable<T>(private val observer: (Subscriber<T>) -> Unit = {}) {
    private var subscribers: List<(T) -> Unit> = mutableListOf()
    private var closed = false

    protected fun emit(value: T) {
        subscribers.forEach {
            it(value)
        }
    }

    protected open fun close()  {
        subscribers = listOf()
        closed = true
    }

    private fun runSubscriber() {
        observer(
            Subscriber({ emit(it) }, { close() })
        )
    }

    open fun subscribe(block: (T) -> Unit): Subscription? {
        if(!closed) {
            subscribers = subscribers + block
            runSubscriber()
            return Subscription {
                subscribers = subscribers.filter { it != block }
            }
        }
        return null
    }

    companion object
}

