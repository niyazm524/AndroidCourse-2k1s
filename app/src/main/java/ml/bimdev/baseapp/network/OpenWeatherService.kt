package ml.bimdev.baseapp.network

import ml.bimdev.baseapp.network.response.WeatherLocationsResponse
import ml.bimdev.baseapp.network.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    suspend fun weatherByCityName(@Query("q") cityName: String): WeatherResponse

    @GET("weather")
    suspend fun weatherByCityId(@Query("id") cityId: Int): WeatherResponse

    @GET("find")
    suspend fun weatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("count") count: Int = 10
    ): WeatherLocationsResponse

}
