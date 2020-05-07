package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

@Suppress("ClassName")
class filter<T>(private val predicate: (T) -> Boolean) : Operator<T, T>() {
    override fun invoke(operand: Observable<T>): Observable<T> {
        return subscribeToSource(operand) { subscriber, value ->
            if (predicate(value)) {
                subscriber.next(value)
            }
        }
    }
}