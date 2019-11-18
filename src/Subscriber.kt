interface Subscriber<T> {
	fun next(item: T)
	fun error(e: Exception) {}
	fun complete() {}
}