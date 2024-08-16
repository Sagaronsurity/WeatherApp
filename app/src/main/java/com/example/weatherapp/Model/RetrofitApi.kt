package com.example.weatherapp.Model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/v1/current.json")
    fun getData(
        @Query("Key") apikey : String,
        @Query("q") city : String
    ) : Call<ResponsePojo>


}