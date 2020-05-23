package ml.bimdev.baseapp.interceptors

import ml.bimdev.baseapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
