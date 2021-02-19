package com.example.campuscontest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscontest.bdd.AppDatabaseHelper
import com.example.campuscontest.bdd.CountryVisitDTO
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CountryVisitAdapter(private var listCountryVisit: MutableList<CountryVisitDTO>, private var mainActivity: MainActivity) :
    RecyclerView.Adapter<CountryVisitAdapter.CountryVisitViewHolder>()
{
    // Crée chaque vue item à afficher :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVisitViewHolder
    {
        val viewCountryVisit = LayoutInflater.from(parent.context).inflate(R.layout.item_country_visit, parent, false)
        return CountryVisitViewHolder(viewCountryVisit, mainActivity)
    }

    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: CountryVisitViewHolder, position: Int)
    {
        Picasso.get().load("http://www.geognos.com/api/en/countries/flag/"+listCountryVisit[position].alpha2Code+".png").into(holder.imgViewPreview);
        holder.textViewName.text = listCountryVisit[position].name
        holder.textViewCapital.text = "Capitale : " + listCountryVisit[position].capital
        holder.textViewRegion.text = "Continent : " + listCountryVisit[position].region
        try {
            val dateFormatOutput: DateFormat = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE)
            val date: String = dateFormatOutput.format(listCountryVisit[position].dateVisite)
            holder.textViewDate.text = date
        } catch (pe: ParseException) {
            holder.textViewDate.text = "Mauvaise date :("
        }

    }

    override fun getItemCount(): Int = listCountryVisit.size

    fun ajouterCountryVisit(countryVisit: CountryVisitDTO)
    {
        listCountryVisit.add(0, countryVisit)
        notifyItemChanged(0)
    }

    fun removeItem(position: Int) {
        val countryVisit = listCountryVisit[position]
        listCountryVisit.removeAt(position)
        AppDatabaseHelper.getDatabase(mainActivity)
            .countryVisitDAO()
            .delete(countryVisit)
        notifyDataSetChanged()
    }

    fun updateCountryVisit(listCountryVisit: MutableList<CountryVisitDTO>)
    {
        this.listCountryVisit = listCountryVisit
        notifyDataSetChanged()
    }

    // ViewHolder :
    inner class CountryVisitViewHolder(itemView: View, mainActivity: MainActivity) : RecyclerView.ViewHolder(itemView)
    {
        val imgViewPreview: ImageView = itemView.findViewById(R.id.flag_country)
        val textViewName: TextView = itemView.findViewById(R.id.name_country)
        val textViewCapital: TextView = itemView.findViewById(R.id.capital_country)
        val textViewRegion: TextView = itemView.findViewById(R.id.region_country)
        val textViewDate: TextView = itemView.findViewById(R.id.date_visit_country)

        val deleteCountryVisit: ImageButton = itemView.findViewById(R.id.delete_visit)

        init {
            deleteCountryVisit.setOnClickListener {
                removeItem(adapterPosition)
            }
        }
    }
}