package com.example.crypto


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.example.crypto.CoinDeatailInfoActivity.Companion.newIntent
import com.example.crypto.adapters.CoinAdapter
import com.example.crypto.pojo.CoinPriceInfo

class CoinListAcitvity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)

        val adapter = CoinAdapter(this)
        val recycler = findViewById<RecyclerView>(R.id.rvCoinList)
        recycler.adapter = adapter
        adapter.onCoinClickListener = object : CoinAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = newIntent(this@CoinListAcitvity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }
}