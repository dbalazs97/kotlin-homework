import kotlin.concurrent.thread

class Observable<T>(private var subscriber: ((Observer<T>) -> Unit)?) {
	private var observers: List<Observer<T>> = listOf()

	fun subscribe(observer: Observer<T>): Subscription {
		observers = observers + listOf(observer)
		thread {
			subscriber?.invoke(observer)
		}
		return Subscription {
			observers = observers - observer
		}
	}

	fun subscribe(subscriber: (T) -> Unit): Subscription {
		return subscribe(Observer(subscriber, null, null))
	}

	companion object {
		fun <T> of(vararg items: T): Observable<T> {
			return from(items.toList())
		}

		fun <T> from(iterable: Iterable<T>): Observable<T> {
			return Observable {
				for (item in iterable) {
					it.next(item)
				}
				it.complete?.invoke()
			}
		}

		fun from(string: String): Observable<Char> {
			return from(string.toList())
		}

		fun <K, V> from(map: Map<K, V>): Observable<Pair<K, V>> {
			return from(map.toList())
		}
	}
}