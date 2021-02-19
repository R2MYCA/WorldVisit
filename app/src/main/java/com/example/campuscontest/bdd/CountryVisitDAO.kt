package com.example.campuscontest.bdd

import androidx.room.*

@Dao
abstract class CountryVisitDAO {
    @Query("SELECT * FROM countryVisit WHERE alpha2Code = :alpha2Code")
    abstract fun getCountryVisitByCode(alpha2Code: String): CountryVisitDTO

    @Query("SELECT * FROM countryVisit ORDER BY dateVisite")
    abstract fun getListCountryVisit(): MutableList<CountryVisitDTO>

    @Insert
    abstract fun insert(vararg countryVisit: CountryVisitDTO)

    @Update
    abstract fun update(vararg countryVisit: CountryVisitDTO)

    @Delete
    abstract fun delete(vararg countryVisit: CountryVisitDTO)
}