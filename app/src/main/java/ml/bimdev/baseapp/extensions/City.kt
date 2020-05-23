package ml.bimdev.baseapp.extensions

import android.graphics.Color
import ml.bimdev.baseapp.data.City
import ml.bimdev.baseapp.network.response.WeatherLocation

fun WeatherLocation.makeCity(): City = City(
    id = this.id,
    name = this.name,
    temp = this.main.temp
)

val City.weatherColor: Int
    get() = when (this.temp.toInt()) {
        in -80..-20 -> Color.rgb(22, 50, 166)
        in -20..-3 -> Color.rgb(50, 110, 166)
        in -3..3 -> Color.rgb(22, 140, 87)
        in 3..20 -> Color.rgb(201, 87, 87)
        in 20..80 -> Color.rgb(176, 16, 16)
        else -> Color.BLACK
    }
