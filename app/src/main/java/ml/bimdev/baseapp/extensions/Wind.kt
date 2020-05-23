package ml.bimdev.baseapp.extensions

import ml.bimdev.baseapp.response.Wind

fun Wind.toWindString(): String {
    val direction = if (deg >= 337.5 && deg <= 22.5) "N"
    else if (deg > 22.5 && deg < 67.5) "NE"
    else if (deg >= 67.5 && deg <= 112.5) "E"
    else if (deg > 112.5 && deg < 157.5) "SE"
    else if (deg >= 157.5 && deg <= 202.5) "S"
    else if (deg > 202.5 && deg < 247.5) "SW"
    else if (deg >= 247.5 && deg <= 292.5) "W"
    else "NW"

    return "${this.speed} m/s $direction"
}
