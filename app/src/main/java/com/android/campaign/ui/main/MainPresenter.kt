package com.android.campaign.ui.main

interface MainPresenter<in V: MainView>{
    fun getCampaigns(view: V, pageNum: Int)
}