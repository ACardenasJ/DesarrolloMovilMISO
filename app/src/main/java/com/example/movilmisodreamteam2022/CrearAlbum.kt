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

class CrearAlbum : AppCompatActivity() {
    var img_basic: String = "https://cdn.dribbble.com/users/1100029/screenshots/5950588/media/451c0eb8bb7675c9bea0ddc26efece44.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        var parentLayout: View = findViewById(android.R.id.content)
        var EDNombreAlbum: EditText = findViewById<EditText>(R.id.EDNombreAlbum)
        var EDYearAlbum: EditText = findViewById<EditText>(R.id.EDYearAlbum)
        var EDArtista: EditText = findViewById<EditText>(R.id.EDArtista)
        var EDCancionPreferida: EditText = findViewById<EditText>(R.id.EDCancionPreferida)
        var EDGenero: EditText = findViewById<EditText>(R.id.EDGenero)
        var EDDescrip: EditText = findViewById<EditText>(R.id.EDDescrip)
        var BtnCrearAlbum: Button = findViewById<Button>(R.id.BtnCrearAlbum)
        var action_Bar = getSupportActionBar()

        // showing the back button in action bar
        if (action_Bar != null) {
            action_Bar.setTitle("CREAR ALBUM")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }
        //supportActionBar?.setTitle("CREAR ARTISTA")
        //supportActionBar?.setDisplayHomeAsUpEnabled(true);


        BtnCrearAlbum.setOnClickListener {
            //Toast.makeText(this,"CREANDO ARTISTA...",Toast.LENGTH_LONG).show()
            //var src : ServiceRC =  ServiceRC()
            //src.getBands()
            Snackbar.make(parentLayout, "CREANDO ALBUM...", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            var nombreAlbum:String = EDNombreAlbum.text.toString()
            var year:String = EDYearAlbum.text.toString()
            var artista:String = EDArtista.text.toString()
            var fav_song:String = EDCancionPreferida.text.toString()
            var gen:String = EDGenero.text.toString()
            var desc:String = EDDescrip.text.toString()

            CrearAlbum_(this, nombreAlbum, year, artista, fav_song, gen, desc)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }

    fun CrearAlbum_(acti: Activity, nombre: String, year:String, artista:String, fav_song: String, gen: String, desc: String){
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val call_funcion = async {ServiceRC().createAlbum(nombre, year, artista, fav_song, gen, desc, img_basic)}
                val retorno = call_funcion.await()
                println(retorno)
                Log.d("Album","resultado creacion:  ${retorno}")
                runOnUiThread(Runnable {
                    Toast.makeText(acti, "Album Creado",Toast.LENGTH_LONG).show()
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