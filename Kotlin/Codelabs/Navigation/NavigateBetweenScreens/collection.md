# Collection

```c
fun main() {
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    println("list:   ${numbers}")
    println("sorted: ${numbers.sorted()}")
    val setOfNumbers = numbers.toSet()
    println("set:	${setOfNumbers}")
    val set1 = setOf(1,2,3)
	val set2 = mutableSetOf(3,2,1)
    println("$set1 == $set2: ${set1 == set2}")
    println("contains 7: ${setOfNumbers.contains(7)}")
    
}
```

## map

- map:  특정 키가 부여된 값을 쉽게 찾을 수 있도록 설계된 키-값 쌍의 집합이다.
- 키는 고유하며 각 키는 정확히 하나의 값에 매핑되지만, 값은 중복될 수 있다.

```c
fun main() {
  val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )
    peopleAges.put("Barbara", 42)
    peopleAges["Joe"] = 51
    peopleAges["Fred"] = 31
    println(peopleAges)
}
```

- forEach: forEach는 현재 항목의 변수를 지정하는 대신 특수 식별자 it을 사용한다
```c
peopleAges.forEach { print("${it.key} is ${it.value}, ") }
```

# 람다
