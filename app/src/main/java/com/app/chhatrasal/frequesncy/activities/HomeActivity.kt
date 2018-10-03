package com.app.chhatrasal.frequesncy.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.app.chhatrasal.frequesncy.R
import com.app.chhatrasal.frequesncy.adapters.CurrencyListVIewAdapter
import com.app.chhatrasal.frequesncy.models.CurrencyListModel
import com.app.chhatrasal.frequesncy.models.DataItem
import com.app.chhatrasal.frequesncy.webService.APIService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var currencyListView: ListView
    private lateinit var adapter: CurrencyListVIewAdapter
    private lateinit var webService: APIService
    private val currencyList: MutableList<DataItem> = ArrayList()

    private val listViewClickListener: AdapterView.OnItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
        val intent = Intent(this@HomeActivity, InfoActivity::class.java)
        intent.putExtra(getString(R.string.name), currencyList[position].name)
        intent.putExtra(getString(R.string.symbol), currencyList[position].symbol)
        intent.putExtra(getString(R.string.rank), currencyList[position].rank)
        intent.putExtra(getString(R.string.usd), currencyList[position].quotes.usd.price)
        intent.putExtra(getString(R.string.inr), currencyList[position].quotes.inr.price)
        intent.putExtra(getString(R.string.percentage24H), currencyList[position].quotes.usd.percentChange24H)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

        clickEvents()
    }

    private fun init() {
        currencyListView = currency_list_view
        adapter = CurrencyListVIewAdapter(this@HomeActivity, currencyList)
        currencyListView.adapter = adapter
        Toast.makeText(this@HomeActivity, "${FirebaseAuth.getInstance().currentUser!!.displayName}", Toast.LENGTH_LONG).show()
    }

    private fun clickEvents() {
        currencyListView.onItemClickListener = listViewClickListener
        webService = Retrofit.Builder()
                .baseUrl(getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
    }

    override fun onResume() {
        super.onResume()
        webService.getCurrencyList().enqueue(object : Callback<CurrencyListModel> {
            override fun onFailure(call: Call<CurrencyListModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CurrencyListModel>, response: Response<CurrencyListModel>) {
                if (response.isSuccessful) {
                    currencyList.clear()
                    currencyList.addAll(response.body()!!.data!!)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
