package com.flurry.android.sample.marketing.messaging.service

import android.util.Log
import com.flurry.android.marketing.messaging.FlurryMessaging
import com.flurry.android.sample.marketing.messaging.PushManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import javax.inject.Inject

class MarketingSampleMessagingService @Inject constructor() : FirebaseMessagingService() {
    @Inject
    lateinit var pushManager: PushManager

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
        Log.i(TAG, "onCreate service")
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        if (!FlurryMessaging.isFlurryMessage(message)) {
            Log.i(TAG, "Non-Flurry Notification received")
            return
        }

        val flurryMessage = FlurryMessaging.convertFcmMessageToFlurryMessage(message)
        Log.i(TAG, "Notification received")
        FlurryMessaging.logNotificationReceived(flurryMessage)
        FlurryMessaging.showNotification(this, flurryMessage)
    }

    override fun onNewToken(token: String?) {
        if (token != null) {
            Log.i(TAG, "Token refreshed: $token")
            pushManager.pushToken = token
            FlurryMessaging.setToken(token)
        }
    }

    companion object {
        private const val TAG = "MessagingService"
    }
}