package com.example.weatherapp.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepo {

   private val _WeatherData = MutableLiveData<NetworkResponce<ResponsePojo>>()
    val WeatherData : LiveData<NetworkResponce<ResponsePojo>> = _WeatherData


    fun fetchData(city: String) {
        RetrofitInstance().api.getData(apiKey().apiKey, city).enqueue(object : Callback<ResponsePojo> {
            override fun onResponse(call: Call<ResponsePojo>, response: Response<ResponsePojo>) {

                _WeatherData.postValue(NetworkResponce.Loading(false))
                if(response.isSuccessful){
                    _WeatherData.postValue(NetworkResponce.Success(response.body()!!))
                }
                else{
                    _WeatherData.postValue(NetworkResponce.Failure("Failed to Load Data"))
                }

            }

            override fun onFailure(call: Call<ResponsePojo>, t: Throwable) {
                _WeatherData.postValue(NetworkResponce.Failure("Failed to Load Data"))
            }
        })
    }

}