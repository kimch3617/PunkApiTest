package com.example.punkapitest.ui.list

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.punkapitest.R
import com.example.punkapitest.base.BaseRecyclerViewAdapter
import com.example.punkapitest.data.Beer
import com.example.punkapitest.data.Beer.Companion.BEER_ITEM
import com.example.punkapitest.databinding.ItemBeerBinding
import com.example.punkapitest.databinding.ItemBeerBlueBinding
import com.example.punkapitest.databinding.ItemBeerGrayBinding
import com.example.punkapitest.ui.detail.BeerDetailActivity

class BeerAdapter : BaseRecyclerViewAdapter<Beer, RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE1 = 1
        private const val TYPE2 = 2
        private const val TYPE3 = 3

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            TYPE1 -> {
                val binding = DataBindingUtil.inflate<ItemBeerBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_beer, parent, false)
                BeerHolder(binding)
            }
            TYPE2 -> {
                val binding = DataBindingUtil.inflate<ItemBeerGrayBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_beer_gray, parent, false)
                BeerGrayHolder(binding)
            }
            TYPE3 -> {
                val binding = DataBindingUtil.inflate<ItemBeerBlueBinding>(
                    LayoutInflater.from(parent.context), R.layout.item_beer_blue, parent, false)
                BeerBlueHolder(binding)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(parent.context, BeerDetailActivity::class.java)
            intent.putExtra(BEER_ITEM, getItem(holder.adapterPosition))
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition) ?: return
        when (holder) {
            is BeerHolder -> {
                val bearImage = holder.binding.beerImage
                Glide.with(bearImage.context).load(item.imageUrl).apply(RequestOptions.centerInsideTransform()).into(bearImage)
                holder.binding.beer = item
            }
            is BeerGrayHolder -> {
                val bearImage = holder.binding.beerImage
                Glide.with(bearImage.context).load(item.imageUrl).apply(RequestOptions.centerInsideTransform()).into(bearImage)
                holder.binding.beer = item
            }
            is BeerBlueHolder -> {
                val bearImage = holder.binding.beerImage
                Glide.with(bearImage.context).load(item.imageUrl).apply(RequestOptions.centerInsideTransform()).into(bearImage)
                holder.binding.beer = item
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % 3) {
            0 -> TYPE1
            1 -> TYPE2
            2 -> TYPE3
            else -> -1
        }
    }

    class BeerHolder(val binding: ItemBeerBinding): RecyclerView.ViewHolder(binding.root)
    class BeerGrayHolder(val binding: ItemBeerGrayBinding): RecyclerView.ViewHolder(binding.root)
    class BeerBlueHolder(val binding: ItemBeerBlueBinding): RecyclerView.ViewHolder(binding.root)
}