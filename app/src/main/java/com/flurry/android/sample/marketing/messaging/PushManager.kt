package com.flurry.android.sample.marketing.messaging

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.flurry.android.sample.marketing.api.model.PushRequest
import com.flurry.android.sample.marketing.api.service.FcmPushService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PushManager @Inject constructor(
    private val notificationManager: NotificationManager,
    private val fcmPushService: FcmPushService
) : Callback<Unit> {
    var pushToken: String? = null

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = notificationManager.getNotificationChannel(CHANNEL_ID)
            if (channel == null) {
                channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = CHANNEL_DESCRIPTION
                    enableLights(true)
                    enableVibration(true)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun triggerFcmNotification(delay: Long, isFlurryNotification: Boolean): Disposable? {
        if (pushToken == null) {
            Log.e(TAG, "FCM Push Token is not avaiable")
            return null
        }

        val flurryPayload = if (isFlurryNotification) PushRequest.RequestData.FlurryPayload(FLURRY_PAYLOAD) else null
        val requestData = PushRequest.RequestData(TEXT_TITLE, TEXT_BODY, TEXT_CLICK_ACTION, PRIORITY, flurryPayload)
        val requestBody = PushRequest(requestData, pushToken!!)
        return Observable.timer(delay, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                fcmPushService.triggerFcmPush(requestBody).enqueue(this)
            }
    }

    override fun onFailure(call: Call<Unit>, t: Throwable) {
        t.printStackTrace()
    }

    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
        if (response.isSuccessful) {
            Log.i(TAG, "Request succeed")
        } else {
            Log.e(TAG, response.errorBody().toString())
        }
    }

    companion object {
        private const val TAG = "PushManager"
        const val CHANNEL_ID = "MarketingSampleAppChannelId"
        private const val CHANNEL_NAME = "Marketing Sample App Channel"
        private const val CHANNEL_DESCRIPTION = "Channel for receiving flurry messages in Marketing Sample app"
        private const val TEXT_TITLE = "Flurry Notification"
        private const val TEXT_BODY = "Click Me"
        private const val TEXT_CLICK_ACTION = ""
        private const val PRIORITY = 2
        private const val FLURRY_PAYLOAD = "Flurry Payload"
    }
}