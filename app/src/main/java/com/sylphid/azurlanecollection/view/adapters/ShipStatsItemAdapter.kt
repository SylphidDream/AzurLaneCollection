package com.sylphid.azurlanecollection.view.adapters

import android.os.Bundle
import com.sylphid.azurlanecollection.view.fragments.ViewModelFragment

class ShipStatsItemAdapter: ViewModelFragment() {

    companion object{
        private const val SHIP_KEY = "ship_key"

        fun getNewStatFragment(statsList: ArrayList<Int>): ShipStatsItemAdapter {
            val fragment = ShipStatsItemAdapter()
            val bundle = Bundle()
            bundle.putIntegerArrayList(SHIP_KEY, statsList)
            fragment.arguments = bundle
            return fragment
        }
    }

}