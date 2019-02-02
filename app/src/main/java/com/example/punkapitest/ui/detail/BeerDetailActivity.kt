package com.example.punkapitest.ui.detail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.punkapitest.R
import com.example.punkapitest.base.BaseActivity
import com.example.punkapitest.data.Beer
import com.example.punkapitest.data.Beer.Companion.BEER_ID
import com.example.punkapitest.data.Beer.Companion.BEER_ITEM
import com.example.punkapitest.databinding.ActivityBeerDetailBinding
import com.example.punkapitest.ui.pay.BeerBuyActivity
import kotlinx.android.synthetic.main.activity_beer_detail.*

class BeerDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val beer = intent.getParcelableExtra<Beer>(BEER_ITEM)
        val binding = DataBindingUtil.setContentView<ActivityBeerDetailBinding>(this, R.layout.activity_beer_detail)
        binding.beer = beer

        Glide.with(image_beer.context).load(beer.imageUrl).apply(RequestOptions.centerInsideTransform()).into(image_beer)
        btn_buy.setOnClickListener {
            val intent = Intent(this, BeerBuyActivity::class.java)
            intent.putExtra(BEER_ID, beer.id)
            startActivity(intent)
            finish()
        }
    }
}
