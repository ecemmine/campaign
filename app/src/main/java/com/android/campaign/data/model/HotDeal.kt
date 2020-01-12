package com.android.campaign.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class HotDeal(
    @SerializedName("title") val title: String? = null,
    @SerializedName("expirationDate") val expirationDate: String? = null)