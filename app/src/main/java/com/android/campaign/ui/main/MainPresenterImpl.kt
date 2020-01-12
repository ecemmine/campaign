package com.android.campaign.ui.main

import android.util.Log
import com.android.campaign.data.model.Campaign
import com.android.campaign.data.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class  MainPresenterImpl<in V: MainView>: MainPresenter<V>{

    override fun getCampaigns(view: V, pageNum: Int){
        NetworkModule().getCampaings(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if(response != null){
                        //prepare list for adapter
                         var items = ArrayList<Campaign>()
                        if (response.banners == null && response.hotDeals != null && response.hotDeals.size > 0){
                            for(hotDeal in response.hotDeals){
                                var campaign = Campaign()
                                campaign.hotDeal = hotDeal
                                items?.add(campaign)
                            }
                        }else if (response.hotDeals == null && response.banners != null && response.banners.size > 0){
                            for(banner in response.banners){
                                var campaign = Campaign()
                                campaign.banner = banner
                                items?.add(campaign)
                            }
                        }else if(response.hotDeals != null &&  response.banners != null && response.hotDeals.size >= response.banners.size){
                            (0 until response.banners.size).forEach { i ->
                                var campaignHotDeal = Campaign()
                                campaignHotDeal.hotDeal = response.hotDeals[i]
                                items?.add(campaignHotDeal)

                                var campaignBanner = Campaign()
                                campaignBanner.banner = response.banners[i]
                                items?.add(campaignBanner)
                            }

                            (response.banners.size until response.hotDeals.size).forEach { i ->
                                var campaignHotDeal = Campaign()
                                campaignHotDeal.hotDeal = response.hotDeals[i]
                                items?.add(campaignHotDeal)
                            }

                        }
                        else if(response.hotDeals != null &&  response.banners != null && response.banners.size > response.hotDeals.size){
                            (0 until response.hotDeals.size).forEach { i ->
                                var campaignHotDeal = Campaign()
                                campaignHotDeal.hotDeal = response.hotDeals[i]
                                items?.add(campaignHotDeal)

                                var campaignBanner = Campaign()
                                campaignBanner.banner = response.banners[i]
                                items?.add(campaignBanner)
                            }

                            (response.hotDeals.size until response.banners.size).forEach { i ->
                                var campaignBanner = Campaign()
                                campaignBanner.banner = response.banners[i]
                                items?.add(campaignBanner)
                            }
                        }
                        if(pageNum == 1){
                            view.loadCampaings(items)
                        }else{
                            view.loadMoreCampaings(items)
                        }

                    }

                }, { error -> Log.d("Error", "Error")}
            )
    }

}