# List
- list는 특정 유형 요쇼의 정렬된 컬렉션이다.
- index는 요소의 위치를 나타내는 정수이다.
- list에서 첫 번째 요소는 list[0]에 있고 마지막 요소는 list.size-1 또는 list.last()에 있다.
- List는 읽기 전용으로, 초기화가 완료되면 수정할 수 없다. 그러나 원본을 변경하지 않고 새 목록을 반환하는 sorted() 및 reversed()와 같은 작업을 적용할 수 있다.
- MutableList는 요소를 추가하거나 삭제, 수정하는 등 만든 후에 수정할 수 있다.
- addAll()을 사용하여 항목 목록을 변경 가능한 목록에 추가할 수 있다.
- while루프를 사용하여 표현식이 false로 될 때까지 코드를 실행하고 루프를 종료한다.
```c
while (expression) {

// While the expression is true, execute this code block

}
```
- for 루프를 사용하여 목록의 모든 항목을 반복한다.
```c
for (item in myList) {

// Execute this code block for each element of the list

}
```

- vararg 수정자를 사용하면 가변적인 인수 수를 함수나 생성자에 전달할 수 있다.

## Solution Code
```c
open class Item(val name: String, val price: Int)

class Noodles: Item("Noodles", 10){
    override fun toString(): String{
        return name 
    }
}

class Vegetables(vararg val topping: String): Item("Vegetables", 5){
    override fun toString(): String{
        if(topping.isEmpty()){
            return "$name Chef's Choice"
        }else{
            return name + " " + topping.joinToString()
        }
        return name + " " + topping.joinToString()
    }
}

class Order(val orderNumber: Int){
    private val itemList = mutableListOf<Item>()
    
    fun addItem(newItem: Item):Order{
        itemList.add(newItem)
        return this
    }
    fun addAll(newItems: List<Item>):Order{
        itemList.addAll(newItems)
        return this
    }
    fun print(){
        println("Order #${orderNumber}")
        var total = 0
        for(item in itemList){
            println("${item}: $${item.price}")
            total +=item.price
        }
        println("Total: $${total}")
    }
}

fun main() {
     val ordersList = mutableListOf<Order>()
    
     val order1 = Order(1)
     order1.addItem(Noodles())
     ordersList.add(order1)
  
          
     val order2 = Order(2)
     order2.addItem(Noodles())
     order2.addItem(Vegetables())
     ordersList.add(order2)
  
     
     val order3 = Order(3)
     val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
     order3.addAll(items)
     ordersList.add(order3)
     
     val order4 = Order(4).addItem(Noodles()).addItem(Vegetables("Cabbageg", "Onion"))
     ordersList.add(order4)
     
     ordersList.add(Order(5).addItem(Noodles()).addItem(Noodles()).addItem(Vegetables("Spinach")))
     
     for(order in ordersList){
         order.print()
         println()
     }
}
```