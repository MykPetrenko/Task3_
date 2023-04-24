package com.example.myapplication

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


const val BaseURL = "https://api.currencyapi.com/"
const val Key = "2RQ2gugowBwNyPSFQOqO0X9RavlBCO8ZuTuHzceN"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRespone()
    }
  private fun getRespone(){
    val retrofit = Retrofit.Builder()
      .baseUrl("https://your-api-url.com/")
      .addConverterFactory(ScalarsConverterFactory.create())
      .build()

    val apiService = retrofit.create(GetCurrencyData::class.java)

    val call = apiService.getData("${BaseURL}v3/latest?apikey=${Key}")
    call.enqueue(object : Callback<String> {
      override fun onResponse(call: Call<String>, response: Response<String>) {

        val responseString = response.body()
        val responseTextView = findViewById<TextView>(R.id.api_response)
        responseTextView.text = responseString
        println("API RESPONSE:")
        println(responseString)
      }

      override fun onFailure(call: Call<String>, t: Throwable) {
        Log.d(ContentValues.TAG, "onFailure: " + t.message)
      }
    })
  }
}
