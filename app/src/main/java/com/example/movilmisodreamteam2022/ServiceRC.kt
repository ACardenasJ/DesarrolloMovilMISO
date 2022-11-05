package com.example.movilmisodreamteam2022

import android.util.Log
import com.example.movilmisodreamteam2022.models.Banda
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class ServiceRC {

    val base_burl : String = "https://backvynils-dtg5.herokuapp.com/"

    suspend fun getBands(): List<Banda> {
        var bandsList: List<Banda> = emptyList()
        try {
            // Your implementation
            val url = URL(base_burl + "bands")
            val connection = url.openConnection() as HttpURLConnection
            if(connection.responseCode == 200){
                val inputSystem = connection.inputStream
                //println(inputSystem.toString())
                //val listType: Type = object : TypeToken<Collection<Banda?>?>() {}.type
                val inputStreamReader = InputStreamReader(inputSystem,"UTF-8")
                //val Bands = Gson().fromJson(inputStreamReader,listType::class.java)
                bandsList = Gson().fromJson(inputStreamReader, Array<Banda>::class.java).toList()
                println(bandsList)
                //return bandsList
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            println(ex.message.toString())
        }
        return bandsList
    }

    suspend fun createBand(nombre: String, year: String, fav_song: String, gen:String, desc:String, imgsrc:String): String{
        var rslt : String = ""
        val jsonObject = JSONObject()
        jsonObject.put("name", nombre)
        jsonObject.put("image", imgsrc)
        jsonObject.put("description", desc)
        jsonObject.put("creationDate", year + "-01-01T00:00:00-05:00")

        val jsonObjectString = jsonObject.toString()
        try {
            val url = URL(base_burl + "bands")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
               /* val response = httpURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                withContext(Dispatchers.Main) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response))
                    Log.d("Pretty Printed JSON :", prettyJson)*/

                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(JsonParser.parseString(httpURLConnection.responseMessage))
                Log.d("Pretty Printed JSON :", prettyJson)
                rslt = prettyJson

            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
                rslt = responseCode.toString()
            }

        }catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            println(ex.message.toString())
        }
        return rslt
    }

    suspend fun createAlbum(nombre: String, year: String, artista:String, fav_song: String, gen:String, desc:String, imgsrc:String): String{
        var rslt : String = ""
        val jsonObject = JSONObject()
        jsonObject.put("name", nombre)
        jsonObject.put("artista", artista)
        jsonObject.put("image", imgsrc)
        jsonObject.put("description", desc)
        jsonObject.put("creationDate", year + "-01-01T00:00:00-05:00")

        val jsonObjectString = jsonObject.toString()
        try {
            val url = URL(base_burl + "")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty("Content-Type", "application/json") // The format of the content we're sending to the server
            httpURLConnection.setRequestProperty("Accept", "application/json") // The format of response we want to get from the server
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(jsonObjectString)
            outputStreamWriter.flush()
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                /* val response = httpURLConnection.inputStream.bufferedReader()
                     .use { it.readText() }  // defaults to UTF-8
                 withContext(Dispatchers.Main) {

                     // Convert raw JSON to pretty JSON using GSON library
                     val gson = GsonBuilder().setPrettyPrinting().create()
                     val prettyJson = gson.toJson(JsonParser.parseString(response))
                     Log.d("Pretty Printed JSON :", prettyJson)*/

                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(JsonParser.parseString(httpURLConnection.responseMessage))
                Log.d("Pretty Printed JSON :", prettyJson)
                rslt = prettyJson

            } else {
                Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
                rslt = responseCode.toString()
            }

        }catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            println(ex.message.toString())
        }
        return rslt
    }


}