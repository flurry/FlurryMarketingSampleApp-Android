package com.flurry.android.sample.marketing.di.module

import com.flurry.android.sample.marketing.api.service.FcmPushService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideFcmPushService(): FcmPushService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT_SECS, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(FcmPushService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://fcm.googleapis.com"
        private const val CLIENT_TIME_OUT_SECS  = 60L
    }
}