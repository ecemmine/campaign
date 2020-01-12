package com.android.campaign.data.model

import com.google.gson.annotations.SerializedName

data class CampaignModel(
    @SerializedName("banners") val banners: List<Banner>? = null,
    @SerializedName("hotDeals") val hotDeals: List<HotDeal>? = null)