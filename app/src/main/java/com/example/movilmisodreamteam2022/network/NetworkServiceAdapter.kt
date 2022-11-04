package com.example.movilmisodreamteam2022.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movilmisodreamteam2022.models.*
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://backvynils-dtg5.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getArtistas( onComplete:(resp:List<Banda>)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("bands",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Banda>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i,
                        Banda(id = item.getInt("id"),
                              name = item.getString("name"),
                              image = item.getString("image"),
                              description = item.getString("description"),
                              creationDate = item.getString("creationDate"))
                    )
                }
                list.sortedByDescending { it.id }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun postArtista(body: JSONObject, onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("bands",
            body,
            Response.Listener<JSONObject> { response ->
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }


    //FUNCIONES PRIN
    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}