package com.example.crypto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDeatailInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_deatail_info)

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                findViewById<TextView>(R.id.detailNameFrom).text = it.fromsymbol
                findViewById<TextView>(R.id.detailNameTo).text = it.tosymbol
                findViewById<TextView>(R.id.detailPrice).text = "Price: " + it.price.toString()
                findViewById<TextView>(R.id.detailPriceMax).text =
                    "Max day price: " + it.highday.toString()
                findViewById<TextView>(R.id.detailPriceMin).text =
                    "Min day price: " + it.lowday.toString()
                findViewById<TextView>(R.id.detailLast).text =
                    "Last market: " + it.lastmarket.toString()
                findViewById<TextView>(R.id.detailUpdate).text =
                    "Last update: " + it.getFormattedTime()
                Picasso.get().load(it.getUrl()).into(findViewById<ImageView>(R.id.detailImg))
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDeatailInfoActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent

        }
    }
}