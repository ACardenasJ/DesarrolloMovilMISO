package com.example.movilmisodreamteam2022.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.models.Coleccionista
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import org.json.JSONObject

class ColeccionistaRepository(val application: Application) {
    fun refreshData(callback: (List<Coleccionista>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getColeccionistas({
            callback(it)
        },
            onError
        )
    }

    fun postData(body: JSONObject ,callback: (JSONObject)->Unit, onError: (VolleyError) -> Unit){
        NetworkServiceAdapter.getInstance(application).postColeccionista(body, callback, onError)
    }

    fun getColeccionistabyId(callback: (Coleccionista)->Unit, onError: (VolleyError)->Unit, id: String) {
        NetworkServiceAdapter.getInstance(application).getColeccionistabyId({
            callback(it)
        },
            onError,
            id
        )
    }
}