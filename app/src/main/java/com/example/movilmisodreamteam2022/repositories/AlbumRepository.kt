package com.example.movilmisodreamteam2022.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Album
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    fun postData(body: JSONObject ,callback: (JSONObject)->Unit, onError: (VolleyError) -> Unit){
        NetworkServiceAdapter.getInstance(application).postAlbum(body, callback, onError)
    }

}