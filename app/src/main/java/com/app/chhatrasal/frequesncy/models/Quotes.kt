package com.app.chhatrasal.frequesncy.models

import com.google.gson.annotations.SerializedName

data class Quotes(@SerializedName("USD")
                  val usd: USD,
                  @SerializedName("INR")
                  val inr: INR)