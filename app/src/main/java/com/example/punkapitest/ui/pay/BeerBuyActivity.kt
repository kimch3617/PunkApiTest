package com.example.punkapitest.ui.pay

import android.content.Intent
import android.os.Bundle
import com.example.punkapitest.R
import com.example.punkapitest.base.BaseActivity
import com.example.punkapitest.data.Beer.Companion.BEER_ID
import com.example.punkapitest.ui.list.BeerActivity
import kotlinx.android.synthetic.main.activity_beer_buy.*

class BeerBuyActivity : BaseActivity() {
    companion object {
        const val BUY_BEER_ID = "buy_beer_id"
        const val BUY_NAME = "buy_name"
        const val BUY_CARD_NUMBER = "buy_card_number"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_buy)

        val beerId = intent.getIntExtra(BEER_ID, -1)
        btn_buy.setOnClickListener {
            val name = input_name.text.toString()
            val cardNumber = input_card.text.toString()

            val intent = Intent(this, BeerActivity::class.java)
            intent.putExtra(BUY_BEER_ID, beerId)
            intent.putExtra(BUY_NAME, name)
            intent.putExtra(BUY_CARD_NUMBER, cardNumber)
            intent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
            startActivity(intent)
        }
    }
}