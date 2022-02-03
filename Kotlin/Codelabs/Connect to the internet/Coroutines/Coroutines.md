# Coroutine
- 동시 실행을 더 유연하고 쉽게 관리할 수 있는 기능을 제공한다.
- 멀티태스킹을 지원하지만 단순히 스레드로 작업하는 것보다 다른 수준의 추상화를 제공한다.

## 기능
- 상태를 저장하여 중단했다가 재개할 수 있다.
- 실행되거나 실행되지 않을 수 있다.
- job: 취소 가능한 작업 단위이다.
- CoroutineScope: launch() 및 async()와 같은 새 코루틴을 만드는 데 사용되는 함수는 CoroutineScope를 확장한다.
- Dispatcher: 코루틴이 사용할 스레드를 결정한다. 새 스레드를 초기화하는 데 드는 성능 비용이 발생하지 않도록 한다.

### runBlocking
- 새 코루틴을 시작하고 완료될 때까지 현재 스레드를 차단하는 것

```c
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ISO_LOCAL_TIME
val time = { formatter.format(LocalDateTime.now()) }

suspend fun getValue(): Double {
    println("entering getValue() at ${time()}")
    delay(3000)
    println("leaving getValue() at ${time()}")
    return Math.random()
}

fun main() {
    runBlocking {
        val num1 = async { getValue() }
        val num2 = async { getValue() }
        println("result of num1 + num2 is ${num1.await() + num2.await()}")
    }
}
```

## 연습문제

- Thread 대신 코루틴을 사용하여 바꾸기

```c
fun main() {
   val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
   repeat(3) {
       Thread {
           println("${Thread.currentThread()} has started")
           for (i in states) {
               println("${Thread.currentThread()} - $i")
               Thread.sleep(50)
           }
       }.start()
   }
}
```

```c
import kotlinx.coroutines.*

fun main() {
   val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
   repeat(3) {
       GlobalScope.launch {
           println("${Thread.currentThread()} has started")
           for (i in states) {
               println("${Thread.currentThread()} - $i")
               delay(5000)
           }
       }
   }
}
```