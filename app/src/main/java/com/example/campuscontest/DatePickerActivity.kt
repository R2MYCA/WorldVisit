package com.example.campuscontest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.campuscontest.bdd.AppDatabaseHelper
import com.example.campuscontest.bdd.CountryVisitDTO
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class DatePickerActivity : AppCompatActivity() {
    var name: String? = "";
    var capital: String? = "";
    var region: String? ="";
    var alpha2Code: String? = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        name = intent.getStringExtra("name")
        capital = intent.getStringExtra("capital")
        region = intent.getStringExtra("region")
        alpha2Code = intent.getStringExtra("alpha2Code")
    }

    fun addCountryVisit(view: View) {
        val datePicker = findViewById<DatePicker>(R.id.datepicker_visit)
        val day = datePicker.dayOfMonth.toString()
        val month = (datePicker.month+1).toString()
        val year = datePicker.year.toString()

        val dateVisit = day+"-"+month+"-"+year
        val dateFormatInput: DateFormat = SimpleDateFormat("d-M-y", Locale.FRANCE)

        val countryVisit = CountryVisitDTO(0, name!!, alpha2Code!!, capital!!, region!!, dateFormatInput.parse(dateVisit))

        AppDatabaseHelper.getDatabase(this)
            .countryVisitDAO()
            .insert(countryVisit)

        //Pour éviter le retour arrière, on peut nettoyer toutes les activités, ça libère de l'espace en même temps
        val i = Intent(this, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }
}