package com.example.campuscontest

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscontest.service.RetourCountryWSGet
import com.squareup.picasso.Picasso

class CountryAdapter(private var listCountry: MutableList<RetourCountryWSGet>, private var chooseCountryActivity: ChooseCountryActivity) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()
{
    // Crée chaque vue item à afficher :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder
    {
        val viewCountry = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(viewCountry, chooseCountryActivity)
    }

    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int)
    {
        Picasso.get().load("http://www.geognos.com/api/en/countries/flag/"+listCountry[position].alpha2Code+".png").into(holder.imgViewPreview);
        holder.textViewName.text = listCountry[position].name
        holder.textViewCapital.text = "Capitale : " + listCountry[position].capital
        holder.textViewRegion.text = "Continent : " + listCountry[position].region
    }

    override fun getItemCount(): Int = listCountry.size

    // ViewHolder :
    inner class CountryViewHolder(itemView: View, chooseCountryActivity: ChooseCountryActivity) : RecyclerView.ViewHolder(itemView)
    {
        val imgViewPreview: ImageView = itemView.findViewById(R.id.flag_country_img)
        val textViewName: TextView = itemView.findViewById(R.id.name_country)
        val textViewCapital: TextView = itemView.findViewById(R.id.capital_country)
        val textViewRegion: TextView = itemView.findViewById(R.id.region_country)

        val chooseCountry: ConstraintLayout = itemView.findViewById(R.id.country_item_box)

        init {
            chooseCountry.setOnClickListener {
                val country = listCountry[adapterPosition]

                val intent = Intent(itemView.context, DatePickerActivity::class.java)
                intent.putExtra("name", country.name);
                intent.putExtra("capital", country.capital);
                intent.putExtra("region", country.region);
                intent.putExtra("alpha2Code", country.alpha2Code);
                itemView.context.startActivity(intent)
            }
        }
    }
}