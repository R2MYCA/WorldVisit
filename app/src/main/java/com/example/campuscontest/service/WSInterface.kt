package com.example.campuscontest.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WSInterface {
    @GET("all")
    fun listCountry(): Call<List<RetourCountryWSGet>>

    @GET("name/{name}")
    fun getByPartialName(@Path("name") name : String): Call<List<RetourCountryWSGet>>
}