package com.flurry.android.sample.marketing.di.module

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SystemServicesModule {
    @Provides
    @Singleton
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}