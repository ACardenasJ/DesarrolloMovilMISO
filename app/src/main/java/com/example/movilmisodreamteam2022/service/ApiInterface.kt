package com.example.movilmisodreamteam2022.service

import com.example.movilmisodreamteam2022.modelos.BandaModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("bands")
    fun fetchAllBands(): Call<List<BandaModel>>

    @POST("bands")
    fun createBanda(@Body bandaModel: BandaModel):Call<BandaModel>

    @PUT("posts/{id}")
    fun updateBanda(@Path("id") id:Int, @Body bandaModel: BandaModel):Call<String>

    @DELETE("posts/{id}")
    fun deleteBanda(@Path("id") id:Int):Call<String>
}