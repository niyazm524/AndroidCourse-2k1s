package ml.bimdev.baseapp

import ml.bimdev.baseapp.network.OpenWeatherService
import ml.bimdev.baseapp.network.interceptors.AuthInterceptor
import ml.bimdev.baseapp.network.interceptors.UnitsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(UnitsInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val openWeatherService: OpenWeatherService by lazy {
        retrofit.create(OpenWeatherService::class.java)
    }

}

