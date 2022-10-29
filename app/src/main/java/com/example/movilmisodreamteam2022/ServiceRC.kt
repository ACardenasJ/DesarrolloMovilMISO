package com.example.movilmisodreamteam2022

import com.example.movilmisodreamteam2022.modelos.BandaModel
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ServiceRC {

    suspend fun getBands(): Int {
        //Thread {
            try {
                // Your implementation
                val url = URL("https://backvynils-dtg5.herokuapp.com/bands")
                val connection = url.openConnection() as HttpURLConnection
                if(connection.responseCode == 200){
                    val inputSystem = connection.inputStream
                    //println(inputSystem.toString())
                    //val listType: Type = object : TypeToken<Collection<Banda?>?>() {}.type
                    val inputStreamReader = InputStreamReader(inputSystem,"UTF-8")
                    //val Bands = Gson().fromJson(inputStreamReader,listType::class.java)

                    var bandsList: List<BandaModel> = Gson().fromJson(inputStreamReader, Array<BandaModel>::class.java).toList()
                    println(bandsList)
                    return bandsList.size
                }
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
                println(ex.message.toString())
            }
        //}.start()
        return -1
    }


}