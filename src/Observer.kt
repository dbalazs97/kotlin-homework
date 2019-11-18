open class Observer<T>(
		val next: (T) -> Unit,
		val error: ((e: Exception) -> Unit)?,
		val complete: (() -> Unit)?
)