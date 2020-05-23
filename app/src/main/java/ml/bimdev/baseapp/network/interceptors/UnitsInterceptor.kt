package ml.bimdev.baseapp.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class UnitsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter("units", "metric")
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
