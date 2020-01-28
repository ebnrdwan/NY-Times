package com.ebnrdwan.core.di


import com.ebnrdwan.core.BuildConfig
import com.ebnrdwan.core.util.Constants
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
object NetworkModule {

    @Singleton
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return Configuration.BaseUrl
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val timeout = Constants.Networking.RETROFIT_TIMEOUT_SECONDS
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .callTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request(Constants.Networking.API_REQUEST_TAG)
                    .response(Constants.Networking.API_RESPONSE_TAG)
                    .addHeader(Constants.Networking.APP_VERSION_TAG, BuildConfig.VERSION_NAME)
                    .build()
            ) // interceptor
            .build() // http client
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(
        @BaseUrl url: String,
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