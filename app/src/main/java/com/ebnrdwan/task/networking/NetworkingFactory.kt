package com.ebnrdwan.task.networking

import com.ebnrdwan.task.BuildConfig
import com.ebnrdwan.task.util.Constants
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkingClient {

    fun provideHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient {
        val timeout = Constants.Networking.RETROFIT_TIMEOUT_SECONDS
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .callTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor) // interceptor
            .build() // http client
    }

    fun provideLoggingInterceptor(): LoggingInterceptor = LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request(Constants.Networking.API_REQUEST_TAG)
        .response(Constants.Networking.API_RESPONSE_TAG)
        .addHeader(Constants.Networking.APP_VERSION_TAG, BuildConfig.VERSION_NAME)
        .build()

    fun provideRetrofitInstance(
        url: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}