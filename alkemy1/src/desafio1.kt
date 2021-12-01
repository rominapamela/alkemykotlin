import java.util.*

fun main(){

    val parking: Parking
    val parkable: Parkable

    parking = Parking(mutableSetOf())
    val vehicle1 = Vehicle("abs123", VehicleType.AUTO, Calendar.getInstance(),"DIACARD001", 360);
    println(parking.addVehicle(vehicle1))
    val vehicle2 = Vehicle("abc123",VehicleType.MINIBUS, Calendar.getInstance(),"DIACARD002", 360);
    println(parking.addVehicle(vehicle2))
    val vehicle3 = Vehicle("xyz123",VehicleType.MINIBUS, Calendar.getInstance(), null, 180);
    println(parking.addVehicle(vehicle3))
    val vehicle4 = Vehicle("sdfg122",VehicleType.MOTO, Calendar.getInstance(),"DIACARD001", 360);
    println(parking.addVehicle(vehicle4))
    val vehicle5 = Vehicle("sdfg123",VehicleType.MOTO, Calendar.getInstance(),"DIACARD001", 50);
    println(parking.addVehicle(vehicle5))
    val vehicle6 = Vehicle("sdfg122",VehicleType.BUS, Calendar.getInstance(),"DIACARD001", 370);
    println(parking.addVehicle(vehicle6))

    parkable = Parkable(parking)
    println(parkable.checkOutVehicle("xyz123"))

    println(parking.showInfo())

    parking.listVehicles()


}

enum class VehicleType {
    AUTO, MOTO, MINIBUS, BUS
}

data class Parkable(val parking: Parking)
{
    fun checkOutVehicle(plate: String): String
    {
        var i = 0
        val size = parking.vehicles.size
        var found = false
        while(i < size) {
            var element = parking.vehicles.elementAt(i)
            if  (plate == element.plate) {
                found = true
                break;
            }
            else {
                i++
            }
        }
        if (found) {

            return "" + onSuccess(parking.vehicles.elementAt(i))

        }

        else
            return "" + onError()
    }
    fun onSuccess(vehicle: Vehicle): String
    {
        var Fee: Int
        if (hasDiscountCard(vehicle.discountCard)) {
            Fee=calculateFee(vehicle.type, vehicle.parkedTime, true)
        }
        else{
            Fee=calculateFee(vehicle.type, vehicle.parkedTime, false)
        }
        parking.updateQuantity(Fee)
        parking.vehicles.remove(vehicle)
        return "Plate found. Your fee es $ " +  Fee + ". Come back soon!!!"

    }

    fun calculateFee(type:VehicleType, minutes:Long, hasCard:Boolean): Int
    {
        var price = 0
        var quarters = 0
        if (type==VehicleType.AUTO){
            price += 20
        }
        if (type==VehicleType.MOTO){
            price += 15
        }
        if (type==VehicleType.MINIBUS){
            price += 25
        }
        if (type==VehicleType.BUS){
            price += 30
        }
        if ((minutes-120)>=0)
        {
            var partInt = (minutes-120)/15
            var partDec = (minutes-120).toDouble()/15 - (minutes-120)/15
            if (partDec>0)
                quarters=(partInt+1).toInt()
            else
                quarters= partInt.toInt()
            price=price+quarters*5
            if (hasCard){
                price= (price*0.85).toInt()
            }
        }
        return price
    }

    fun hasDiscountCard(discountCard: String?):Boolean
    {
        if (discountCard == null){
            return false
        }
        else{
            return true
        }
    }

    fun onError(): String
    {
        return "Plate not found. Sorry, the Check-out failed."
    }

}

data class Parking(val vehicles: MutableSet<Vehicle>)
{
    var maximo=0
    var cantVehicle=0
    var total=0
    var quantity: Pair<Int, Int> = Pair(cantVehicle,total)

    fun addVehicle(vehicle: Vehicle): Boolean
    {
        if (maximo<20) {
            vehicles.add(vehicle)
            maximo = maximo + 1
            return true
        }
        else{
            return false
        }
    }

    fun updateQuantity(fee: Int){
        cantVehicle += 1
        total += fee
        quantity = Pair (cantVehicle, total)
    }

    fun showInfo(): String{
        return "" + quantity.first + " vehicles have checked out and have earnings of $" + quantity.second
    }

    fun listVehicles(){
        for(element in vehicles)
            println(element.plate.toString())
    }
}

data class Vehicle(val plate: String, val type: VehicleType, val checkInTime: Calendar, val discountCard: String?, val parkedTime: Long)
{
    override fun equals(other: Any?): Boolean{
        if (other is Vehicle){
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }
}