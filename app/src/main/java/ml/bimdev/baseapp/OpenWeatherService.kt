package ml.bimdev.baseapp

import ml.bimdev.baseapp.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    suspend fun weatherByCityName(@Query("q") cityName: String): WeatherResponse

}
