package com.example.punkapitest.repository

import com.example.punkapitest.data.Beer
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @GET("beers")
    fun getBeers(@Query("page") page: Int, @Query("beer_name") beerName: String, @Query("per_page") perPage: Int = 20): Call<ArrayList<Beer>>
}