package com.example.campuscontest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscontest.service.ReseauHelper
import com.example.campuscontest.service.RetourCountryWSGet
import com.example.campuscontest.service.RetrofitSingleton
import com.example.campuscontest.service.WSInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseCountryActivity : AppCompatActivity() {
    private lateinit var countryAdapter: CountryAdapter
    private val service = RetrofitSingleton.retrofit.create(WSInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_country)

        findViewById<RecyclerView>(R.id.liste_box).setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.liste_box).layoutManager = layoutManager

        getListCountry();
    }

    fun getListCountry()
    {
        findViewById<ProgressBar>(R.id.loader).setVisibility(View.VISIBLE);
        if (ReseauHelper.estConnecte(this)) {
            val call = service.listCountry()
            call.enqueue(object : Callback<List<RetourCountryWSGet>>
            {
                override fun onResponse(call: Call<List<RetourCountryWSGet>>, response: Response<List<RetourCountryWSGet>>)
                {
                    if (response.isSuccessful)
                    {
                        findViewById<ProgressBar>(R.id.loader).setVisibility(View.GONE);
                        val retourWSGet = response.body()
                        if(response.body() != null && !response.body().isNullOrEmpty()) {
                            countryAdapter = CountryAdapter(response.body()!!.toMutableList(), this@ChooseCountryActivity)
                            findViewById<RecyclerView>(R.id.liste_box).adapter = countryAdapter
                        }
                        Log.d("world-visit-danger", "$retourWSGet")
                    }
                }
                override fun onFailure(call: Call<List<RetourCountryWSGet>>, t: Throwable)
                {
                    findViewById<ProgressBar>(R.id.loader).setVisibility(View.GONE);
                    Log.e("world-visit-except","${t.message}")
                }
            })
        }
    }

    fun getWithPartialName(view: View)
    {
        val name = findViewById<EditText>(R.id.search_country_input).getText().toString();
        findViewById<ProgressBar>(R.id.loader).setVisibility(View.VISIBLE);
        if (ReseauHelper.estConnecte(this)) {
            val call = service.getByPartialName(name)
            call.enqueue(object : Callback<List<RetourCountryWSGet>>
            {
                override fun onResponse(call: Call<List<RetourCountryWSGet>>, response: Response<List<RetourCountryWSGet>>)
                {
                    if (response.isSuccessful)
                    {
                        findViewById<ProgressBar>(R.id.loader).setVisibility(View.GONE);
                        val retourWSGet = response.body()
                        if(response.body() != null && !response.body().isNullOrEmpty()) {
                            countryAdapter = CountryAdapter(response.body()!!.toMutableList(), this@ChooseCountryActivity)
                            findViewById<RecyclerView>(R.id.liste_box).adapter = countryAdapter
                        }
                        Log.d("world-visit-danger", "$retourWSGet")
                    }
                }
                override fun onFailure(call: Call<List<RetourCountryWSGet>>, t: Throwable)
                {
                    findViewById<ProgressBar>(R.id.loader).setVisibility(View.GONE);
                    Log.e("world-visit-except","${t.message}")
                }
            })
        }
    }
}