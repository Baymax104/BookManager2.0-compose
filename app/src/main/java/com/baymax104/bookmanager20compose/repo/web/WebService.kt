package com.baymax104.bookmanager20compose.repo.web

import android.util.Log
import com.baymax104.bookmanager20compose.repo.API_KEY
import com.baymax104.bookmanager20compose.repo.BASE_URL
import com.baymax104.bookmanager20compose.util.JsonCoder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
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
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        val apiKeyFilter = Interceptor {
            val url = it.request().url.newBuilder()
                .addQueryParameter("uKey", API_KEY)
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
            .addConverterFactory(JsonCoder.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create()
}

/**
 * 创建网络服务实例
 * @param S 服务接口类型
 * @return 服务实例
 */
inline fun <reified S> createService(): Lazy<S> = lazy { WebService.create() }