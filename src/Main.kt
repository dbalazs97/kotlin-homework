fun main() {
	val observableList = Observable.from(listOf(1,2,3,4,5))
	val observableString = Observable.from("Hello world")
	val observableMap = Observable.from(mapOf("a" to 1, "b" to 2, "c" to 3))
	val observableOf = Observable.of(1,2, 'a', "asd")

	observableList.subscribe { Thread.sleep(1000);print("List: ");println(it) }
	observableString.subscribe { Thread.sleep(700);print("String: ");println(it) }
	observableMap.subscribe { Thread.sleep(500);print("Map: ");println(it) }
	observableOf.subscribe { Thread.sleep(100);print("Of: ");println(it) }

}