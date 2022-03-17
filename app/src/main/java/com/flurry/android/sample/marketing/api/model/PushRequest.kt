package com.flurry.android.sample.marketing.api.model

import com.google.gson.annotations.SerializedName

data class PushRequest(
    @SerializedName("data") val data: RequestData,
    @SerializedName("to") val token: String
) {

    data class RequestData(
        @SerializedName("title") val title: String,
        @SerializedName("body") val body: String,
        @SerializedName("click_action") val clickAction: String,
        @SerializedName("priority") val priority: Int,
        @SerializedName("fl.Data") val flurryPayload: FlurryPayload? = null
    ) {

        data class FlurryPayload(@SerializedName("flurryPayload") val payload: String)

    }

}
