package com.bacl.solution.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkBuilder private constructor() {
    private val appApi: AppApi

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private fun provideOkHttpClientGlobal(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        val paramtersInterceptor = Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                    .build()
            val request = original.newBuilder()
                    .url(url)
                    .build()
            val response = chain.proceed(request)
            response
        }
        return OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(paramtersInterceptor)
                .build()
    }

    companion object {
        fun build(): NetworkBuilder {
            return NetworkBuilder()
        }

        fun api(): AppApi {
            return build().appApi
        }

        fun provideGson(): Gson {
            return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sshhhh.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(provideOkHttpClientGlobal())
            .build()
        appApi = retrofit.create(AppApi::class.java)

    }
}