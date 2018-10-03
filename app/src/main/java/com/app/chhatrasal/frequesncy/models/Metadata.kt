package com.app.chhatrasal.frequesncy.models

import com.google.gson.annotations.SerializedName

data class Metadata(@SerializedName("num_cryptocurrencies")
                    val numCryptocurrencies: Int = 0,
                    @SerializedName("error")
                    val error: String? = null,
                    @SerializedName("timestamp")
                    val timestamp: Int = 0)