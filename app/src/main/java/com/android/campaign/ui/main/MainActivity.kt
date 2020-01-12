package com.android.campaign.ui.main

import android.nfc.tech.MifareUltralight
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.campaign.R
import com.android.campaign.data.model.Campaign
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val mPresenter by lazy { MainPresenterImpl<MainView>() }
    private lateinit var  adapter : MainAdapter
    var isLoaded: Boolean = true
    var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun initViews() {
        mPresenter.getCampaigns(this, 1)
    }

    override fun loadCampaings(campaign: ArrayList<Campaign>) {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        adapter = MainAdapter(campaign)
        campaignList?.layoutManager = layoutManager
        campaignList?.adapter = adapter

        //set scroll listener for pagination
        val recyclerViewOnScrollListener = object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if(dy>0){
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= MifareUltralight.PAGE_SIZE && isLoaded) {
                             pageNumber += 1
                             isLoaded = false
                             mPresenter.getCampaigns(this@MainActivity, pageNumber)
                    }

                }
            }
        }
        campaignList?.addOnScrollListener(recyclerViewOnScrollListener)
    }

    override fun loadMoreCampaings(campaign: ArrayList<Campaign>){
        //load items to lsit
        isLoaded = true
        adapter.addCampaing(campaign)
    }
}
