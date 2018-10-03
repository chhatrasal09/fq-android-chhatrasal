package com.app.chhatrasal.frequesncy.models

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("symbol")
                    val symbol: String = "",
                    @SerializedName("circulating_supply")
                    val circulatingSupply: Double = 0.0,
                    @SerializedName("last_updated")
                    val lastUpdated: Int = 0,
                    @SerializedName("total_supply")
                    val totalSupply: Double = 0.0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("max_supply")
                    val maxSupply: Double = 0.0,
                    @SerializedName("rank")
                    val rank: Int = 0,
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("website_slug")
                    val websiteSlug: String = "",
                    @SerializedName("quotes")
                    val quotes: Quotes)