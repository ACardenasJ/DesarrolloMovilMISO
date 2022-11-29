package com.example.movilmisodreamteam2022.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import org.json.JSONObject

class ArtistaRepository (val application: Application){

    fun refreshData(callback: (List<Banda>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getArtistas({
            callback(it)
        },
            onError
        )
    }

    fun postData(body: JSONObject ,callback: (JSONObject)->Unit, onError: (VolleyError) -> Unit){
        NetworkServiceAdapter.getInstance(application).postArtista(body, callback, onError)
    }

    fun getArtistaByID(callback: (Banda)->Unit, onError: (VolleyError)->Unit, id: String) {
        NetworkServiceAdapter.getInstance(application).getArtistabyId({
                callback(it)
            },
                onError,
                id
        )

    }

}