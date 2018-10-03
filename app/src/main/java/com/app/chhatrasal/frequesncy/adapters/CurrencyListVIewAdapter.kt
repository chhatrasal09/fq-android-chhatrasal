package com.app.chhatrasal.frequesncy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.chhatrasal.frequesncy.R
import com.app.chhatrasal.frequesncy.models.CurrencyListModel
import com.app.chhatrasal.frequesncy.models.DataItem

class CurrencyListVIewAdapter(private var context: Context, private var list: List<DataItem>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView: View = inflater.inflate(R.layout.currency_list_item, parent, false)
        val currencyNameTextView: TextView = rootView.findViewById(R.id.currency_name_text_view)
        val currencySymbolTextVIew: TextView = rootView.findViewById(R.id.currency_symbol_text_view)
        val currencyPriceTextView: TextView = rootView.findViewById(R.id.currency_price_text_view)

        currencyNameTextView.text = list[position].name
        currencySymbolTextVIew.text = list[position].symbol
        currencyPriceTextView.text = list[position].quotes.inr.price.toFloat().toString()
        return rootView
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if (list == null) {
            return 0
        }
        return list.size
    }


}