package com.flurry.android.sample.marketing.di.component

import android.app.Application
import com.flurry.android.sample.marketing.MarketingSampleApplication
import com.flurry.android.sample.marketing.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class, ActivityModule::class,
    SystemServicesModule::class,
    NetworkModule::class
])
interface AppComponent {
    fun inject(marketingSampleApplication: MarketingSampleApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}