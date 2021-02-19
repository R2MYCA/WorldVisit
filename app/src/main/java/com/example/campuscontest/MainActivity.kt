package com.example.campuscontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscontest.bdd.AppDatabaseHelper
import com.example.campuscontest.bdd.CountryVisitDTO
import com.example.campuscontest.service.ReseauHelper
import com.example.campuscontest.service.RetourCountryWSGet
import com.example.campuscontest.service.RetrofitSingleton
import com.example.campuscontest.service.WSInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var countryVisitAdapter: CountryVisitAdapter
    private lateinit var countryAdapter: CountryAdapter
    private val service = RetrofitSingleton.retrofit.create(WSInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.liste_box).setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.liste_box).layoutManager = layoutManager

        val listCountryVisit: MutableList<CountryVisitDTO> = AppDatabaseHelper.getDatabase(this)
            .countryVisitDAO()
            .getListCountryVisit()
        countryVisitAdapter = CountryVisitAdapter(listCountryVisit, this, )

        findViewById<RecyclerView>(R.id.liste_box).adapter = countryVisitAdapter
        findViewById<ProgressBar>(R.id.loader).setVisibility(View.GONE);
    }

    /*Rafraichir la liste*/
    override fun onResume() {
        super.onResume()
        val listCountryVisit: MutableList<CountryVisitDTO> = AppDatabaseHelper.getDatabase(this)
            .countryVisitDAO()
            .getListCountryVisit()
        countryVisitAdapter = CountryVisitAdapter(listCountryVisit, this)
        findViewById<RecyclerView>(R.id.liste_box).adapter = countryVisitAdapter
    }

    fun refreshListCountryVisit(favoris: CountryVisitDTO, add: Boolean = false)
    {
        val listCountryVisit: MutableList<CountryVisitDTO> = AppDatabaseHelper.getDatabase(this)
            .countryVisitDAO()
            .getListCountryVisit()
        countryVisitAdapter.updateCountryVisit(listCountryVisit)
    }

    fun addCountry(view: View) {
        val intent = Intent(this, ChooseCountryActivity::class.java)
        startActivity(intent)
    }
}