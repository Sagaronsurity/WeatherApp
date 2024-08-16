package com.example.weatherapp.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Model.NetworkResponce
import com.example.weatherapp.Model.ResponsePojo
import com.example.weatherapp.Model.RetrofitData
import com.example.weatherapp.Model.RetrofitRepo

class WeatherViewModel : ViewModel() {

    val repo = RetrofitRepo()
    val weatherData : LiveData<NetworkResponce<ResponsePojo>> = repo.WeatherData
    fun GetData(city : String){
       repo.fetchData(city)
    }

}