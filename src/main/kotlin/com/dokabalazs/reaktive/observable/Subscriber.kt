package com.dokabalazs.reaktive.observable

class Subscriber<T>(val next: (value: T) -> Unit, val complete: () -> Unit)