package com.flurry.android.sample.marketing

import android.app.Service
import android.util.Log
import com.flurry.android.FlurryAgent
import com.flurry.android.marketing.FlurryMarketingModule
import com.flurry.android.marketing.FlurryMarketingOptions
import com.flurry.android.sample.marketing.messaging.PushManager
import com.google.firebase.iid.FirebaseInstanceId
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class MarketingSampleApplication : BaseApplication(), HasServiceInjector {
    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()

        FlurryAgent.Builder()
            .withLogLevel(Log.VERBOSE)
            .withLogEnabled(true)
            .withModule(createMarketingModule())
            .build(this, getString(R.string.FLURRY_APIKEY))

        refreshFcmToken()
    }

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun createMarketingModule(): FlurryMarketingModule {
        pushManager.createNotificationChannel()

        val options = FlurryMarketingOptions.Builder()
            .withDefaultNotificationChannelId(PushManager.CHANNEL_ID)
            .withDefaultNotificationIconResourceId(R.drawable.ic_notification)
            .withDefaultNotificationIconAccentColor(R.color.colorAccent)
            .build()

        return FlurryMarketingModule(options)
    }

    private fun refreshFcmToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            pushManager.pushToken = it.result?.token
        }
    }
}