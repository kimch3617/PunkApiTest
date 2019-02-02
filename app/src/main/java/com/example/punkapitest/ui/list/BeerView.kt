package com.example.punkapitest.ui.list

import com.example.punkapitest.base.BaseView
import com.example.punkapitest.data.Beer

interface BeerView : BaseView {
    fun setBeers(beers: ArrayList<Beer>)
}