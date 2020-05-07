package com.dokabalazs.reaktive.observable

fun <T> Observable.Companion.from(items: List<T>): Observable<T> {
    return Observable {
        for (item in items) {
            it.next(item)
        }
        it.complete()
    }
}

fun <T> Observable.Companion.of(item: T): Observable<T> {
    return Observable {
        it.next(item)
        it.complete()
    }
}

fun <T> Observable.Companion.fromSource(observable: Observable<T>): Observable<T> {
    return Observable {
        observable.subscribe { value ->
            it.next(value)
        }
    }
}