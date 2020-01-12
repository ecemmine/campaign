package com.android.campaign.data.model

import com.google.gson.annotations.SerializedName

data class Campaign(
    @SerializedName("banner") var banner: Banner? = null,
    @SerializedName("hotDeal") var hotDeal: HotDeal? = null)