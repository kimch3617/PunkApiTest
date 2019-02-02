package com.example.punkapitest.ui.list

import com.example.punkapitest.base.BasePresenter
import com.example.punkapitest.data.Beer
import com.example.punkapitest.repository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerPresenter : BasePresenter<BeerView>() {
    fun getBeers(page: Int, beerName: String) {
        RemoteRepository.create().getBeers(page, beerName).enqueue(object : Callback<ArrayList<Beer>> {
            override fun onResponse(call: Call<ArrayList<Beer>>, response: Response<ArrayList<Beer>>) {
                response.body()?.let { beers ->
                    getView()?.setBeers(beers)
                }
            }

            override fun onFailure(call: Call<ArrayList<Beer>>, t: Throwable) {
                getView()?.showToast(t.localizedMessage)
            }
        })
    }
}