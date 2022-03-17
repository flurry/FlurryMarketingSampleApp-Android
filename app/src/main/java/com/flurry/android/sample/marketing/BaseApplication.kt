package com.flurry.android.sample.marketing

import android.app.Activity
import android.app.Application
import com.flurry.android.sample.marketing.di.component.DaggerAppComponent
import com.flurry.android.sample.marketing.messaging.PushManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

abstract class BaseApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var pushManager: PushManager

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this as MarketingSampleApplication)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityAndroidInjector

    companion object {
        internal const val TAG = "MarketingSampleApp"
    }
}