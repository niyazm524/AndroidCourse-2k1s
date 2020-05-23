package ml.bimdev.baseapp.extensions

import ml.bimdev.baseapp.network.response.Wind

val DIRECTIONS = arrayOf(
    "N",
    "NNE",
    "NE",
    "ENE",
    "E",
    "ESE",
    "SE",
    "SSE",
    "S",
    "SSW",
    "SW",
    "WSW",
    "W",
    "WNW",
    "NW",
    "NNW"
)

fun Wind.toWindString(): String {
    val degree = ((deg / 22.5) + 0.5).toInt()
    val direction = DIRECTIONS[degree % 16]
    return "${this.speed} m/s $direction"
}
