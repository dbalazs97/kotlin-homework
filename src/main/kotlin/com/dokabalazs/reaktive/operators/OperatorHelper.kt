package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable
import com.dokabalazs.reaktive.observable.Subscriber

fun <T, R> subscribeToSource(
    source: Observable<T>,
    block: (subscriber: Subscriber<R>, value: T) -> Unit
): Observable<R> {
    return Observable { subscriber ->
        source.subscribe {
            block(subscriber, it)
        }
    }
}

fun <A, B, C> unify(op1: Operator<A, B>, op2: Operator<B, C>): Operator<A, C> {
    return object : Operator<A, C>() {
        override fun invoke(operand: Observable<A>): Observable<C> {
            return op2(op1(operand))
        }
    }
}