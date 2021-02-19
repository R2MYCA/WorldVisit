package com.example.campuscontest.bdd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.campuscontest.utils.Converters

@Database(entities = [CountryVisitDTO::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryVisitDAO(): CountryVisitDAO
}