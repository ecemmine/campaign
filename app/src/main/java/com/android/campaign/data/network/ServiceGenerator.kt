package com.android.campaign.data.network

import com.android.campaign.data.model.CampaignModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface  ServiceGenerator {

    @Headers("accept: */*")
    @GET("campaigns/{pageNumber}")
    fun getCampaigns(@Path("pageNumber") pageNumber: Int): Single<CampaignModel>

}