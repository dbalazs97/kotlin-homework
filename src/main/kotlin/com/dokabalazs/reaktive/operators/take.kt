package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

@Suppress("ClassName")
class take<T>(private val itemCount: Int) : Operator<T, T>() {
    private var invokeCounter = 0

    override fun invoke(operand: Observable<T>): Observable<T> {
        return subscribeToSource(operand) { subscriber, value ->
            if (invokeCounter < itemCount) {
                subscriber.next(value)
                invokeCounter++
            }
        }
    }
}