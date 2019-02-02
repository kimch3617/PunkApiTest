package com.example.punkapitest.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(val id: Int, val name: String, val firstBrewed: String, val description: String, val imageUrl: String,
                val abv: Float, val ibu: Float, val ebc: Float, val srm: Float, val ph: Float, val brewersTips: String, val contributedBy: String): Parcelable {
    companion object {
        const val BEER_ID = "beer_id"
        const val BEER_ITEM = "beer_item"
    }
}