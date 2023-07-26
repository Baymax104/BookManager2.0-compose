package com.baymax104.bookmanager20compose.repo.web

import android.util.Log
import com.baymax104.bookmanager20compose.repo.API_KEY
import com.baymax104.bookmanager20compose.repo.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Retrofit网络服务
 * @author John
 */
object WebService {

    val retrofit: Retrofit

    init {

        val logger = HttpLoggingInterceptor {
            Log.i("BookManager-log-web", it)
        }.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val apiKeyFilter = Interceptor {
            val url = it.request().url.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .build()
            val request = it.request()
                .newBuilder()
                .url(url)
                .build()
            it.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(apiKeyFilter)
            .retryOnConnectionFailure(true)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

}