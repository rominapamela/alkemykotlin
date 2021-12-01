class Mentoria2 {
    private lateinit var name: String //variable inicializada tarde
    private val lastname: String by lazy {
        //lazy var solo se instancia cuando la necesitas
        println("la variable lazy lastname ha sido despertada")
        "Pratto"
    }

    private var student: Pair<String, String> ?= null

    ///logica de codigo

    init {
        //getName("Romina")
        getStudent()
    }

    private fun getName(name: String){
        this.name = name //inicialización del lateinit var
        if (this::name.isInitialized) {
            println("${this.name} ${this.lastname}") //lastname es la llamada al lazy val
        }
        else println("No se ha inicializado la propiedad name")
    }

    private fun getStudent(){
        /*val animal = Animal()
        animal.tipo = "oso"
        animal.años = "14"*/

        val animal = Animal().apply {
            this.tipo = "oso"
            this.años = "14"
        }

        //this.student = Pair("Kevin", "Escobedo")
        this.student?.let {
            println(student)
        } ?: kotlin.run{
            println("Student es nulo y no tiene información")
            println(animal.tipo)
        }

    }
}

class GenericClass<T>(val id: T)

class Animal{
    var tipo: String = ""
    var años: String = ""
}

fun main() {
    Mentoria2()
    val cancion = GenericClass(6.889)
    println(cancion.id::class.simpleName)
}