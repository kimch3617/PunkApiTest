package com.example.punkapitest.ui.list

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.example.punkapitest.R
import com.example.punkapitest.base.BaseActivity
import com.example.punkapitest.data.Beer
import com.example.punkapitest.databinding.ActivityBeersBinding
import com.example.punkapitest.ui.pay.BeerBuyActivity.Companion.BUY_BEER_ID
import com.example.punkapitest.ui.pay.BeerBuyActivity.Companion.BUY_CARD_NUMBER
import com.example.punkapitest.ui.pay.BeerBuyActivity.Companion.BUY_NAME
import com.example.punkapitest.util.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_beers.*

class BeerActivity : BaseActivity(), BeerView {
    companion object {
        private const val DEFAULT_QUERY = "_"
    }
    private lateinit var mPresenter: BeerPresenter
    private lateinit var mAdapter: BeerAdapter

    private var mPage = 1
    private var mNameQuery = DEFAULT_QUERY
    private var mIsEmpty = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBeersBinding>(this, R.layout.activity_beers)
        binding.isEmpty = mIsEmpty

        mPresenter = BeerPresenter()
        mPresenter.bindView(this)

        mPresenter.getBeers(mPage, mNameQuery)

        mAdapter = BeerAdapter()
        val manager = LinearLayoutManager(this)
        recycler_beer.adapter = mAdapter
        recycler_beer.layoutManager = manager
        recycler_beer.addOnScrollListener(object: PaginationScrollListener(manager) {
            override fun loadMoreItems() {
                mPresenter.getBeers(mPage, mNameQuery)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        val searchViewItem = menu.findItem(R.id.menu_action_search)

        val searchViewActionBar = searchViewItem.actionView as SearchView
        searchViewActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                resetBeerList(q ?: DEFAULT_QUERY)
                searchViewActionBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(q: String?): Boolean {
                return false
            }
        })

        searchViewActionBar.setOnCloseListener {
            resetBeerList()
            false
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val beerId =  it.getIntExtra(BUY_BEER_ID, -1)
            val buyName =  it.getStringExtra(BUY_NAME) ?: ""
            val buyCardNumber =  it.getStringExtra(BUY_CARD_NUMBER) ?: ""
            showToast("BeerId: $beerId, Name: $buyName, Card: $buyCardNumber")
        }
    }

    private fun resetBeerList(query: String = DEFAULT_QUERY) {
        mPage = 1
        mNameQuery = query
        mAdapter.removeAll()
        mPresenter.getBeers(mPage, mNameQuery)
    }

    override fun setBeers(beers: ArrayList<Beer>) {
        if (!beers.isEmpty()) mPage++
        mAdapter.addItems(beers)

        if (mAdapter.itemCount == 0) mIsEmpty.set(true)
        else mIsEmpty.set(false)
    }
}
