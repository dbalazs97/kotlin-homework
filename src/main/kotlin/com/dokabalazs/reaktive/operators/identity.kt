package com.dokabalazs.reaktive.operators

import com.dokabalazs.reaktive.observable.Observable

@Suppress("ClassName")
class identity<T>: Operator<T, T>() {
    override fun invoke(operand: Observable<T>): Observable<T> {
       return operand
    }
}