package com.dokabalazs.reaktive

import com.dokabalazs.reaktive.observable.Observable
import com.dokabalazs.reaktive.observable.from
import com.dokabalazs.reaktive.observable.of
import com.dokabalazs.reaktive.observable.pipe
import com.dokabalazs.reaktive.operators.*
import com.dokabalazs.reaktive.subject.BehaviorSubject
import com.dokabalazs.reaktive.subject.Subject
import com.dokabalazs.reaktive.subject.WebSocketSubject
import java.math.BigDecimal

data class Person(val name: String, val age: Int)

val people: List<Person> = listOf(
    Person("Anna", 22),
    Person("Balazs", 22),
    Person("Clerk", 31),
    Person("Derrick", 14),
    Person("Elon", 65),
    Person("Franck", 6)
)

fun example1() {
    Observable.of(10).subscribe { println(it) }
}

fun example2() {
    Observable.from(listOf(1, 2, 3, 4)).subscribe { println(it) }
}

fun example3() {
    val observable: Observable<Int> = Observable.from(listOf(1, 2, 3, 4))
    observable.subscribe { println("First observer: $it") }
    observable.subscribe { println("Second observer: $it") }
}

fun example4() {
    val observable: Observable<Int> = Observable.from(listOf(1, 2, 3, 4))
    observable.pipe(
        map { it * it },
        filter<Int> { it > 8 }
    ).subscribe {
        println(it)
    }
}

fun example5() {
    val observable: Observable<Person> = Observable.from(people)
    observable.pipe(
        filter { it.name[0] <= 'D' },
        filter { it.age < 25 },
        take(2),
        map { it.name },
        tap { println("Name: $it") },
        filter { it.length > 2 },
        identity<String>()
    ).subscribe {
        println(it)
    }
}

fun example6() {
    val subject: Subject<Int> = Subject()
    val behaviorSubject: BehaviorSubject<Int> = BehaviorSubject(0)
    subject.subscribe { println("Subject: $it, BehaviorSubject: ${behaviorSubject.value}") }
    behaviorSubject.subscribe { println("Behavior Subject: $it"); subject.next(it * 2) }
    behaviorSubject.next(10)
    subject.next(10)
    behaviorSubject.next(20)
    subject.next(20)
    behaviorSubject.next(30)
    subject.next(30)
}

fun example7() {
    val webSocketSubject = WebSocketSubject("ws://echo.websocket.org/")
    webSocketSubject
        .pipe(
            map { it.split(' ') },
            map<List<String>, Pair<BigDecimal, Int>> { it[0].toBigDecimal() to it[1].toInt() },
            map {
                "RTT=${(System.currentTimeMillis().toBigDecimal() - it.first).toInt()}ms" to "ID=${it.second}"
            },
            identity<Pair<String, String>>()
        )
        .subscribe { (rtt, id) ->
            println("Message from WebSocket (${rtt}, ${id})")
        }

    repeat(10) {
        webSocketSubject.next("${System.currentTimeMillis()} $it")
        Thread.sleep(300L)
    }
    webSocketSubject.close()
}

fun main() {
    println("-- Example 1 --"); example1()
    println("-- Example 2 --"); example2()
    println("-- Example 3 --"); example3()
    println("-- Example 4 --"); example4()
    println("-- Example 5 --"); example5()
    println("-- Example 6 --"); example6()
    println("-- Example 7 --"); example7()
}
