package ml.bimdev.baseapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import kotlinx.android.synthetic.main.activity_weather_details.*
import kotlinx.coroutines.*
import ml.bimdev.baseapp.extensions.toWindString
import ml.bimdev.baseapp.network.OpenWeatherService
import ml.bimdev.baseapp.network.response.WeatherResponse
import kotlin.coroutines.CoroutineContext
import kotlin.math.roundToInt

class WeatherDetailsActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var weatherService: OpenWeatherService
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Weather"
        weatherService = ApiFactory.openWeatherService

        val cityId: Int = intent.getIntExtra("cityId", 1)
        loadWeatherByCityId(cityId)
    }

    private fun loadWeatherByCityId(cityId: Int) = launch(Dispatchers.Main) {
        val response = withContext(coroutineContext) { weatherService.weatherByCityId(cityId) }
        bindWeatherToViews(response)
    }


    private fun bindWeatherToViews(weatherResponse: WeatherResponse) {
        supportActionBar?.title = "Weather in ${weatherResponse.name}"
        tv_city.text = weatherResponse.name
        tv_clouds.text = weatherResponse.weathers.firstOrNull()?.main ?: "Unknown"
        tv_temp.text =
            getString(R.string.temp_celsius, weatherResponse.main.temp.roundToInt().toString())
        tv_temp_max.text = getString(R.string.temp_celsius, weatherResponse.main.tempMax.toString())
        tv_temp_min.text = getString(R.string.temp_celsius, weatherResponse.main.tempMin.toString())
        tv_wind.text = weatherResponse.wind.toWindString()
        tv_pressure.text = getString(R.string.pressure, weatherResponse.main.pressure.toString())
        tv_humidity.text = getString(R.string.humidity, weatherResponse.main.humidity.toString())
        iv_clouds.load("http://openweathermap.org/img/w/${weatherResponse.weathers.first().icon}.png")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
