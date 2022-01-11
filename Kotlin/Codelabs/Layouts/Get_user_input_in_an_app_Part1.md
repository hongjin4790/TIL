# Get user input in an app: Part 1

## Kotlin의 클래스 및 상속

### 기본 클래스 만들기
```c
fun main() {
    val dwelling = Dwelling()
}


abstract class Dwelling(private var residents: Int) {

   abstract val buildingMaterial: String
   abstract val capacity: Int

   fun hasRoom(): Boolean {
       return residents < capacity
   }
}

```

### 서브클래스 만들기


![12](https://user-images.githubusercontent.com/29851704/148950977-f17f750d-0b9c-44cb-8121-3e7f9c20d9e8.png)

```c
import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    val squareCabin = SquareCabin(6,50.0)
    val roundHut = RoundHut(3,10.0)
    val roundTower = RoundTower(4,15.5)
    
    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
    
    with(roundHut){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Floor area: ${floorArea()}")
        println("Carpet size: ${calculateMaxCarpetSize()}")
    }
    
    with(roundTower) {
    println("\nRound Tower\n==========")
    println("Material: ${buildingMaterial}")
    println("Capacity: ${capacity}")
    println("Has room? ${hasRoom()}")
    println("Floor area: ${floorArea()}")
    println("Carpet size: ${calculateMaxCarpetSize()}")
}
    
}


abstract class Dwelling(private var residents: Int) {

   abstract val buildingMaterial: String
   abstract val capacity: Int

   fun hasRoom(): Boolean {
       return residents < capacity
   }
   
   abstract fun floorArea(): Double
    
    fun getRoom(){
        if (capacity > residents){
            residents++
            println("you got a room!")
        }else{
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

class SquareCabin(residents: Int, val length: Double): Dwelling(residents){
    override val buildingMaterial = "wood"
    override val capacity = 6
    override fun floorArea(): Double{
        return length*length
    }
}

open class RoundHut(val residents: Int, val radius: Double): Dwelling(residents){
    override val buildingMaterial = "Straw"
    override val capacity = 4
    override fun floorArea(): Double{
        return PI*radius*radius
    }
    fun calculateMaxCarpetSize(): Double{
        val diameter = 2* radius
        return sqrt(diameter * diameter / 2)
    }

}

class RoundTower(residents: Int, radius: Double, val floors: Int = 2): RoundHut(residents, radius){
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
    override fun floorArea(): Double{
        return super.floorArea()*floors
    }
    
}


```