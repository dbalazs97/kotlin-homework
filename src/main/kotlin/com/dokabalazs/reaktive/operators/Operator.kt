package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

abstract class Operator<T, R> {
    abstract operator fun invoke(operand: Observable<T>): Observable<R>
    operator fun <R1>div(operand: Operator<R, R1>): Operator<T, R1> {
        return unify(this, operand)
    }
}


