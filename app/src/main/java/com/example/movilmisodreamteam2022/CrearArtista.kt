package com.example.movilmisodreamteam2022

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CrearArtista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista)

        supportActionBar?.setTitle("CREAR ARTISTA")

        val BtnCrearArtista: Button = findViewById<Button>(R.id.BtnCrearArtista)
        BtnCrearArtista.setOnClickListener {
            Toast.makeText(this,"CREANDO ARTISTA...",Toast.LENGTH_LONG).show()
            //var src : ServiceRC =  ServiceRC()
            //src.getBands()
            CrearArtista_()
        }
    }

    fun CrearArtista_(){
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val call_funcion = async {ServiceRC().getBands()}
                Log.d("Crear_Artistas","Tamaño es ${call_funcion.await()}")

            }
        }
    }


}