package com.example.movilmisodreamteam2022.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.network.NetworkServiceAdapter
import org.json.JSONObject

class ArtistaRepository (val application: Application){
    fun refreshData(callback: (List<Banda>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getArtistas({
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    fun postData(body: JSONObject ,callback: (JSONObject)->Unit, onError: (VolleyError) -> Unit){
        NetworkServiceAdapter.getInstance(application).postArtista(body, callback, onError)
    }

}