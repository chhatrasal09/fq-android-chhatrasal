package com.app.chhatrasal.frequesncy.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.app.chhatrasal.frequesncy.R
import com.google.firebase.auth.FirebaseAuth

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<TextView>(R.id.header_text_view).text = "Welcome!!!\n${FirebaseAuth.getInstance().currentUser!!.displayName}"
        findViewById<TextView>(R.id.currency_name_text_view).text = intent.getStringExtra(getString(R.string.name))
        findViewById<TextView>(R.id.currency_symbol_text_view).text = intent.getStringExtra(getString(R.string.symbol))
        findViewById<TextView>(R.id.currency_rank_text_view).text = intent.getIntExtra(getString(R.string.rank), 0).toString()
        findViewById<TextView>(R.id.currency_price_usd_text_view).text = intent.getDoubleExtra(getString(R.string.usd), 0.0).toFloat().toString()
        findViewById<TextView>(R.id.currency_price_inr_text_view).text = intent.getDoubleExtra(getString(R.string.inr), 0.0).toFloat().toString()
        Log.v("#####", "change value => ${intent.getDoubleExtra(getString(R.string.percentage24H),0.0)}")
        if (intent.getDoubleExtra(getString(R.string.percentage24H), 0.0) > 0) {
            findViewById<ImageView>(R.id.percentage_24_image_view).setImageDrawable(getDrawable(R.drawable.profit))
        } else {
            findViewById<ImageView>(R.id.percentage_24_image_view).setImageDrawable(getDrawable(R.drawable.loss))
        }
    }
}
