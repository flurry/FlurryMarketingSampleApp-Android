package com.flurry.android.sample.marketing.api.service

import com.flurry.android.sample.marketing.api.model.PushRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FcmPushService {
    @POST("fcm/send")
    @Headers(
        "Content-Type: application/json",
        "Authorization: key=$SERVER_KEY"
    )
    fun triggerFcmPush(@Body pushRequest: PushRequest): Call<Unit>

    companion object {
        private const val SERVER_KEY = "...... Put Your Firebase Cloud Messaging Server key here ..............................................................................................."
    }
}
