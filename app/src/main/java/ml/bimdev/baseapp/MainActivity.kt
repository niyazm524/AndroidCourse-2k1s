package ml.bimdev.baseapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ml.bimdev.baseapp.data.City
import ml.bimdev.baseapp.extensions.getLastLocationSuspended
import ml.bimdev.baseapp.extensions.makeCity
import ml.bimdev.baseapp.network.OpenWeatherService
import ml.bimdev.baseapp.recycler.CityAdapter
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var weatherService: OpenWeatherService
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val adapter: CityAdapter = CityAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_cities.adapter = adapter
        adapter.setOnItemClickListener { city ->
            startActivity(Intent(this, WeatherDetailsActivity::class.java).apply {
                putExtra("cityId", city.id)
            })
        }
        weatherService = ApiFactory.openWeatherService
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkPermissions()
        launch(Dispatchers.Main) {
            val citiesAsync = async(coroutineContext) { loadNearCitiesWeather() }
            adapter.submitList(citiesAsync.await().also { Log.w("str", it.joinToString(", ")) })
        }
    }

    private suspend fun loadNearCitiesWeather(): List<City> {
        var location: Location? = null
        if (checkPermissions()) {
            location = fusedLocationClient.getLastLocationSuspended()
        }
        val coords =
            if (location != null) location.latitude to location.longitude else 55.830433 to 49.066082
        val response = weatherService.weatherByLocation(coords.first, coords.second)
        return response.list.map { weatherLocation -> weatherLocation.makeCity() }
    }

    private fun checkPermissions(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQ_CODE
            )
            false
        } else true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQ_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val LOCATION_REQ_CODE = 3
    }
}
