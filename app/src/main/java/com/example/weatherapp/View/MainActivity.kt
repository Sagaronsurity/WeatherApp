package com.example.weatherapp.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherapp.Model.NetworkResponce
import com.example.weatherapp.Model.ResponsePojo
import com.example.weatherapp.Model.RetrofitData
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.GONE

        viewmodel.weatherData.observe(this, Observer { state ->
            when (state) {
                is NetworkResponce.Loading -> {
                    binding.progressBar.visibility = if (state.is_loading) View.VISIBLE else View.GONE
                }
                is NetworkResponce.Success -> {
                    updateWeather(state.data)
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkResponce.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.searchbutn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewmodel.GetData(binding.intputcity.text.toString())
        }
    }

    private fun updateWeather(data: ResponsePojo) {
        binding.txtlocation.text = "${data.location?.name} ${data.location?.country}"
        Glide.with(this)
            .load("https:${data.current?.condition?.icon}".replace("64x64", "128x128"))
            .into(binding.weatherimg)
        binding.weather.text = data.current?.condition?.text
        binding.humidity.text = data.current?.humidity.toString()
        binding.localdate.text = "${data.current?.heatindexC}.C"
        binding.localtime.text = "${data.current?.dewpointC}.C"
        binding.uv.text = data.current?.uv.toString()
        binding.participation.text = "${data.current?.precipIn} mm"
        binding.windspeed.text = "${data.current?.windKph} Km/h"
        binding.temp.text = "${data.current?.tempC}.C"
    }
}
