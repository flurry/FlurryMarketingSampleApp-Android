package com.flurry.android.sample.marketing.di.module

import com.flurry.android.sample.marketing.messaging.service.MarketingSampleMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract fun provideMarketingSampleMessagingService(): MarketingSampleMessagingService
}