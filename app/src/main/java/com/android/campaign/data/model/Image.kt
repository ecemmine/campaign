package com.android.campaign.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("width") val width: Int? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("url") val url: String? = null)