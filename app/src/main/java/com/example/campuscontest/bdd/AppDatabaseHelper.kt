package com.example.campuscontest.bdd

import android.content.Context
import androidx.room.Room

class AppDatabaseHelper(context: Context) {
    companion object {
        private lateinit var databaseHelper: AppDatabaseHelper

        fun getDatabase(context: Context): AppDatabase {
            if (!::databaseHelper.isInitialized) {
                databaseHelper = AppDatabaseHelper(context)
            }
            return databaseHelper.database
        }
    }

    val database: AppDatabase = Room
        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "countryvisit.db")
        .allowMainThreadQueries()
        .build()
}