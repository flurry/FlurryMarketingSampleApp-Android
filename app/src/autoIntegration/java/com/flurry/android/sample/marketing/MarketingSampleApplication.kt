package com.flurry.android.sample.marketing

import android.util.Log
import android.widget.Toast
import com.flurry.android.FlurryAgent
import com.flurry.android.marketing.FlurryMarketingModule
import com.flurry.android.marketing.FlurryMarketingOptions
import com.flurry.android.marketing.messaging.FlurryMessagingListener
import com.flurry.android.marketing.messaging.notification.FlurryMessage
import com.flurry.android.sample.marketing.messaging.PushManager

class MarketingSampleApplication : BaseApplication() {
    private val messagingListener = object : FlurryMessagingListener {
        override fun onNotificationReceived(message: FlurryMessage?): Boolean {
            Log.i(TAG, "Notification received")
            Toast.makeText(this@MarketingSampleApplication, "Notification received", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onNotificationClicked(message: FlurryMessage?): Boolean {
            Log.i(TAG, "Notification clicked")
            Toast.makeText(this@MarketingSampleApplication, "Notification clicked", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onNonFlurryNotificationReceived(nonFlurryMessage: Any?) {
            Log.i(TAG, "Non-Flurry Notification received")
            Toast.makeText(this@MarketingSampleApplication, "Non-Flurry Notification received", Toast.LENGTH_SHORT).show()
        }

        override fun onTokenRefresh(token: String?) {
            if (token != null) {
                Log.i(TAG, "Token refreshed: $token")
                pushManager.pushToken = token
                Toast.makeText(this@MarketingSampleApplication, "Token refreshed", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onNotificationCancelled(message: FlurryMessage?) {
            Log.i(TAG, "Notification cancelled")
            Toast.makeText(this@MarketingSampleApplication, "Notification cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate() {
        super.onCreate()

        FlurryAgent.Builder()
            .withLogEnabled(true)
            .withLogLevel(Log.VERBOSE)
            .withModule(createMarketingModule())
            .build(this, getString(R.string.FLURRY_APIKEY));
    }

    private fun createMarketingModule(): FlurryMarketingModule {
        pushManager.createNotificationChannel()

        val options = FlurryMarketingOptions.Builder()
            .setupMessagingWithAutoIntegration()
            .withFlurryMessagingListener(messagingListener)
            .withDefaultNotificationChannelId(PushManager.CHANNEL_ID)
            .withDefaultNotificationIconResourceId(R.drawable.ic_notification)
            .withDefaultNotificationIconAccentColor(R.color.colorAccent)
            .build()

        return FlurryMarketingModule(options)
    }
}