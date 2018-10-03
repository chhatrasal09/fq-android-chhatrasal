package com.app.chhatrasal.frequesncy.webService

import com.app.chhatrasal.frequesncy.models.CurrencyListModel
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/v2/ticker/?convert=INR&structure=array")
    fun getCurrencyList(): Call<CurrencyListModel>
}