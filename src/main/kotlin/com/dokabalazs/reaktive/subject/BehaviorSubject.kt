package com.dokabalazs.reaktive.subject

import com.dokabalazs.reaktive.observable.Subscription

class BehaviorSubject<T>(value: T) : Subject<T>() {
    var value: T = value
        private set

    override fun next(value: T) {
        this.value = value
        emit(value)
    }

    override fun subscribe(block: (T) -> Unit): Subscription? {
        block(value)
        return super.subscribe(block)
    }
}