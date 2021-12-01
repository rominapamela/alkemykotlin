fun main(){
    val name = "Romina" //variable inmutable, no puede ser reasignada
    var surname = "Velazquez" //variable mutable, surname = "otro apellido"
    println(name+surname)

    var listGenres = mutableListOf("Pop", "Rock", "Reggeaton")
    listGenres.add("Bachata")

    val a = 101
    val b = 3
    val rest = a % b
    println(rest)

    val value = "TAS9FlJwaPV"
    val result = value.take(5).contains('a', true)
    println(result)

    try {
        val result = 2 / 0
        print ( result )
    } catch (e: Exception) {
        print (e)
    }

    val total = 10
    val resultado = fibonacci(total)
    println("Primeros $total elementos de la serie de Fibonacci: $resultado")

}

fun fibonacci(n: Int = 5): MutableList<Long> {
    return if(n > 0) {

        val list = mutableListOf<Long>(0)
        var aux = 1L
        var fib = 0L

        (2 .. n).forEach {
            aux += fib
            fib = aux - fib
            list.add(fib)
        }

        list
    } else mutableListOf<Long>()
}




