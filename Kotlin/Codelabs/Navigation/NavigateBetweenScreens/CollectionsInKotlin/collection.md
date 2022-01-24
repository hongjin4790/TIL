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

- sortedWith(): 두 Strings 사이의 일련의 비교를 한꺼번에 처리하여 이름 길이를 기준으로 오름차순으로 목록을 출력합니다.

```c
fun main() {
    val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")
    val filteredWords = words.filter { it.startsWith("b", ignoreCase = true) }
        .shuffled()
        .take(2)
        .sorted()
    println(filteredWords)
}
```

- startsWith(): 대소문자를 구분하지않고 지정한 문자로 필터링한다.
- shuffled(): 항목이 무작위로 섞인 컬렉션 사본을 만들 수 있다.
- take(2): 필터링된 단어에서 처음 2개의 뒤섞인 단어가 포함되도록 한다.