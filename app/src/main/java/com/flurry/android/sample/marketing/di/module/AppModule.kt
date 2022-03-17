package com.flurry.android.sample.marketing.di.module

import android.app.NotificationManager
import android.content.Context
import com.flurry.android.sample.marketing.MarketingSampleApplication
import com.flurry.android.sample.marketing.api.service.FcmPushService
import com.flurry.android.sample.marketing.messaging.PushManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: MarketingSampleApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun providePushMananger(notificationManager: NotificationManager, fcmPushService: FcmPushService): PushManager
            = PushManager(notificationManager, fcmPushService)
}