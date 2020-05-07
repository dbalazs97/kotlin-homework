package com.dokabalazs.reaktive.subject

import com.dokabalazs.reaktive.observable.Observable

open class Subject<T> : Observable<T>() {
    open fun next(value: T) {
        emit(value)
    }
}