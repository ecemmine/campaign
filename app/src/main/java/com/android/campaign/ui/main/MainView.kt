package com.android.campaign.ui.main

import com.android.campaign.data.model.Campaign

interface MainView{
    fun initViews()
    fun loadCampaings(campaign: ArrayList<Campaign>)
    fun loadMoreCampaings(campaign: ArrayList<Campaign>)
}