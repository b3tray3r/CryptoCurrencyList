package com.example.crypto.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.crypto.R
import com.example.crypto.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinAdapter(val context: Context) : Adapter<CoinAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update)
                symbol.text = String.format(symbolsTemplate, fromsymbol, tosymbol)
                symbolPrice.text = price?.toString()
                lastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(getUrl()).into(holder.image)
                itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }

    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.symbolPic)
        val symbol: TextView = itemView.findViewById(R.id.symbols)
        val symbolPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val lastUpdate: TextView = itemView.findViewById(R.id.lastUpdate)
    }

    interface OnCoinClickListener {
       fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }


}