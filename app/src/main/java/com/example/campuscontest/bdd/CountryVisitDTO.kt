package com.example.campuscontest.bdd

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "countryVisit")
class CountryVisitDTO (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val alpha2Code: String = "",
    val capital: String = "",
    val region: String = "",
    val dateVisite: Date?) : Parcelable
