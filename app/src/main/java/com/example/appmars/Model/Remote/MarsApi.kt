package com.example.appmars.Model.Remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MarsApi {
    // vieja confiable
    @GET("realestate")
    fun fetchMarsData(): Call<List<Mars>>


    // parte 2 coroutines
    @GET("realestate")
    suspend fun fetchMarsDataCoroutines(): Response<List<Mars>>

}