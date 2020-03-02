package ml.bimdev.baseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var service: OpenWeatherService

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = ApiFactory.openWeatherService
        launch {
            val response = withContext(Dispatchers.IO) {
                service.weatherByCityName("Kazan")
            }
            // tv_info.text = "Температура: ${response.main.temp.toInt()} гр."
        }
    }
}
