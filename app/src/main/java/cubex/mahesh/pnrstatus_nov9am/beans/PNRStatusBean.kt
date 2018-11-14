package cubex.mahesh.pnrstatus_nov9am.beans

data class PNRStatusBean(
    var total_passengers:Int,
    var doj:String,
    var train:Train,
    var from_station:FromStation,
    var to_station:ToStation,
    var passengers:MutableList<Passenger>)