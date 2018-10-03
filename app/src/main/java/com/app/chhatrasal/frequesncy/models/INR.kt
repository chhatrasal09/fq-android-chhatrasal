package com.app.chhatrasal.frequesncy.models

import com.google.gson.annotations.SerializedName

data class INR(@SerializedName("percent_change_1h")
               val percentChange1H: Double = 0.0,
               @SerializedName("market_cap")
               val marketCap: Double = 0.0,
               @SerializedName("percent_change_24h")
               val percentChange24H: Double = 0.0,
               @SerializedName("price")
               val price: Double = 0.0,
               @SerializedName("volume_24h")
               val volumeH: Double = 0.0,
               @SerializedName("percent_change_7d")
               val percentChange7D: Double = 0.0)