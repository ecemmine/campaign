package com.android.campaign.data.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("image") val image: Image? = null)