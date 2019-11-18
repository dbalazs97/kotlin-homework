class Subscription(private val unsubscribeFunction: () -> Unit) {
	fun unsubscribe() {
		this.unsubscribeFunction()
	}
}