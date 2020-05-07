package com.dokabalazs.reaktive.observable

import com.dokabalazs.reaktive.operators.Operator

fun <T, R> Observable<T>.pipe(
    op1: Operator<T, R>
): Observable<R> {
    return op1(this)
}

fun <T, R1, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R>
): Observable<R> {
    return op2(op1(this))
}

fun <T, R1, R2, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R>
): Observable<R> {
    return op3(op2(op1(this)))
}

fun <T, R1, R2, R3, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R3>,
    op4: Operator<R3, R>
): Observable<R> {
    return op4(op3(op2(op1(this))))
}

fun <T, R1, R2, R3, R4, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R3>,
    op4: Operator<R3, R4>,
    op5: Operator<R4, R>
): Observable<R> {
    return op5(op4(op3(op2(op1(this)))))
}

fun <T, R1, R2, R3, R4, R5, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R3>,
    op4: Operator<R3, R4>,
    op5: Operator<R4, R5>,
    op6: Operator<R5, R>
): Observable<R> {
    return op6(op5(op4(op3(op2(op1(this))))))
}

fun <T, R1, R2, R3, R4, R5, R6, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R3>,
    op4: Operator<R3, R4>,
    op5: Operator<R4, R5>,
    op6: Operator<R5, R6>,
    op7: Operator<R6, R>
): Observable<R> {
    return op7(op6(op5(op4(op3(op2(op1(this)))))))
}

fun <T, R1, R2, R3, R4, R5, R6, R7, R> Observable<T>.pipe(
    op1: Operator<T, R1>,
    op2: Operator<R1, R2>,
    op3: Operator<R2, R3>,
    op4: Operator<R3, R4>,
    op5: Operator<R4, R5>,
    op6: Operator<R5, R6>,
    op7: Operator<R6, R7>,
    op8: Operator<R7, R>
): Observable<R> {
    return op8(op7(op6(op5(op4(op3(op2(op1(this))))))))
}