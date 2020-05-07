package com.dokabalazs.reaktive.observable

class Subscription(private val unSubscribeFunction: () -> Unit) {
    fun unsubscribe() = unSubscribeFunction()
}