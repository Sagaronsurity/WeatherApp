package com.example.weatherapp.Model

sealed class NetworkResponce<out T> {
    
    data class Success<out T>(val data : T) : NetworkResponce<T>()
    data class Loading(val is_loading : Boolean) : NetworkResponce<Nothing>()
    data class Failure(val message : String) : NetworkResponce<Nothing>()
    
}
