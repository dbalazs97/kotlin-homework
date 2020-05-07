package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

@Suppress("ClassName")
class tap<T>(private val sideEffect: (T) -> Unit) : Operator<T, T>() {
    override fun invoke(operand: Observable<T>): Observable<T> {
        return subscribeToSource(operand) { subscriber, value ->
            sideEffect(value)
            subscriber.next(value)
        }
    }
}