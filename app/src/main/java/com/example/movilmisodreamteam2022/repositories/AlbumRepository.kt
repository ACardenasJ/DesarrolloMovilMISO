package com.example.movilmisodreamteam2022.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Album
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }

    fun postData(body: JSONObject ,callback: (JSONObject)->Unit, onError: (VolleyError) -> Unit){
        NetworkServiceAdapter.getInstance(application).postAlbum(body, callback, onError)
    }

}