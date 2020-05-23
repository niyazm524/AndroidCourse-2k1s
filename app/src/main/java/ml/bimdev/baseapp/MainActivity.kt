package ml.bimdev.baseapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.SearchView
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
import ml.bimdev.baseapp.network.response.WeatherResponse
import ml.bimdev.baseapp.recycler.CityAdapter
import retrofit2.HttpException
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
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearchCity(query)
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })
        swipeRefreshLayout.setOnRefreshListener {
            loadCitiesList()
        }
        adapter.setOnItemClickListener { city ->
            openDetailsScreen(city.id)
        }
        weatherService = ApiFactory.openWeatherService
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkPermissions()
        loadCitiesList()
    }

    private fun loadCitiesList() = launch(Dispatchers.Main) {
        val citiesAsync = async(coroutineContext) { loadNearCitiesWeather() }
        swipeRefreshLayout.isRefreshing = false
        adapter.submitList(citiesAsync.await())
    }

    private suspend fun loadNearCitiesWeather(): List<City> {
        var location: Location? = null
        if (checkPermissions()) {
            location = fusedLocationClient.getLastLocationSuspended()
        }
        val coords = if (location != null)
            location.latitude to location.longitude
        else PREDEFINED_LOCATION_LAT to PREDEFINED_LOCATION_LON

        val response = weatherService.weatherByLocation(coords.first, coords.second)
        return response.list.map { weatherLocation -> weatherLocation.makeCity() }
    }

    private fun onSearchCity(cityName: String) = launch(Dispatchers.Main) {
        try {
            val response: WeatherResponse =
                withContext(coroutineContext) { weatherService.weatherByCityName(cityName) }
            val cityId = response.id
            if (cityId != null) {
                openDetailsScreen(cityId)
            }
        } catch (exception: HttpException) {
            Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun openDetailsScreen(cityId: Int) {
        startActivity(Intent(this, WeatherDetailsActivity::class.java).apply {
            putExtra("cityId", cityId)
        })
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
        const val PREDEFINED_LOCATION_LAT = 55.830433
        const val PREDEFINED_LOCATION_LON = 49.066082
    }
}
