package com.example.movilmisodreamteam2022

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.movilmisodreamteam2022.modelos.BandaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class CrearArtista : AppCompatActivity() {
    var img_basic: String = "https://cdn.dribbble.com/users/1100029/screenshots/5950588/media/451c0eb8bb7675c9bea0ddc26efece44.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista)
        var parentLayout: View = findViewById(android.R.id.content)
        var EDNombre: EditText = findViewById<EditText>(R.id.EDNombre)
        var EDYear: EditText = findViewById<EditText>(R.id.EDYear)
        var EDSong: EditText = findViewById<EditText>(R.id.EDSong)
        var EDGenero: EditText = findViewById<EditText>(R.id.EDGenero)
        var EDDesc: EditText = findViewById<EditText>(R.id.EDDesc)
        var BtnCrearArtista: Button = findViewById<Button>(R.id.BtnCrearArtista)
        var action_Bar = getSupportActionBar()

        // showing the back button in action bar
        if (action_Bar != null) {
            action_Bar.setTitle("CREAR ARTISTA")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }
        //supportActionBar?.setTitle("CREAR ARTISTA")
        //supportActionBar?.setDisplayHomeAsUpEnabled(true);


        BtnCrearArtista.setOnClickListener {
            //Toast.makeText(this,"CREANDO ARTISTA...",Toast.LENGTH_LONG).show()
            //var src : ServiceRC =  ServiceRC()
            //src.getBands()
            Snackbar.make(parentLayout, "CREANDO ARTISTA...", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            var nombre:String = EDNombre.text.toString()
            var year:String = EDYear.text.toString()
            var fav_song:String = EDSong.text.toString()
            var gen:String = EDGenero.text.toString()
            var desc:String = EDDesc.text.toString()

            CrearArtista_(this, nombre, year, fav_song, gen, desc)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }

    fun CrearArtista_(acti: Activity, nombre: String, year:String, fav_song: String, gen: String, desc: String){
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val call_funcion = async {ServiceRC().createBand(nombre, year, fav_song, gen, desc, img_basic)}
                val retorno = call_funcion.await()
                println(retorno)
                Log.d("Artistas","resultado creacion:  ${retorno}")
                runOnUiThread(Runnable {
                    Toast.makeText(acti, "Artista Creado",Toast.LENGTH_LONG).show()
                    finish()
                })
            }
        }
    }

    fun ObtenerArtistas_(acti: Activity){
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val call_funcion = async {ServiceRC().getBands()}
                //Log.d("Crear_Artistas","Tamaño es ${call_funcion.await()}")
                //val retorno = call_funcion.await()
                var bandsList: List<BandaModel> = emptyList()
                bandsList = call_funcion.await()
                bandsList.forEach {
                    println("Name:$it.name")
                    Log.d("Artistas","Banda  ${it.name}")
                }
                Log.d("Artistas","Tamaño es ${bandsList.size}")
                runOnUiThread(Runnable {
                    Toast.makeText(acti, "Tamaño: ${bandsList.size}",Toast.LENGTH_LONG).show()
                })
            }
        }
    }


}