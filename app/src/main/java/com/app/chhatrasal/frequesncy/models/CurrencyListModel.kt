package com.app.chhatrasal.frequesncy.models

import com.google.gson.annotations.SerializedName

data class CurrencyListModel(@SerializedName("metadata")
                             val metadata: Metadata,
                             @SerializedName("data")
                             val data: List<DataItem>?)