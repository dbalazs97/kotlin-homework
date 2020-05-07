package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

@Suppress("ClassName")
class map<T, R>(private val mapper: (T) -> R) : Operator<T, R>() {
    override fun invoke(operand: Observable<T>): Observable<R> {
        return subscribeToSource(operand) { subscriber, value ->
            subscriber.next(mapper(value))
        }
    }
}