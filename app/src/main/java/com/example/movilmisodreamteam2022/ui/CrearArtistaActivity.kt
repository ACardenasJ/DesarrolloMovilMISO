package com.example.movilmisodreamteam2022.ui

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.viewmodels.ArtistaViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.system.measureTimeMillis


class CrearArtistaActivity : AppCompatActivity() {
    var img_basic: String = "https://cdn.dribbble.com/users/1100029/screenshots/5950588/media/451c0eb8bb7675c9bea0ddc26efece44.png"
    private lateinit var viewModel: ArtistaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartista)
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

        viewModel = ViewModelProvider(this, ArtistaViewModel.Factory(this.application)).get(ArtistaViewModel::class.java)


        BtnCrearArtista.setOnClickListener {
            //Toast.makeText(this,"CREANDO ARTISTA...",Toast.LENGTH_LONG).show()
            //var src : ServiceRC =  ServiceRC()
            //src.getBands()


            var nombre:String = EDNombre.text.toString()
            var year:String = EDYear.text.toString()
            var fav_song:String = EDSong.text.toString()
            var gen:String = EDGenero.text.toString()
            var desc:String = EDDesc.text.toString()

            if(!nombre.isNullOrEmpty() && !year.isNullOrEmpty() && !desc.isNullOrEmpty() ) {
                Snackbar.make(parentLayout, "CREANDO ARTISTA...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                CrearArtista_(this, nombre, year, fav_song, gen, desc)
            }else{
                Snackbar.make(parentLayout, "LOS VALORES NO PUEDEN SER VACIOS", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }

    fun CrearArtista_(acti: Activity, nombre: String, year:String, fav_song: String, gen: String, desc: String){

        val postParams = mapOf<String, Any>(
            "name" to nombre,
            "image" to img_basic,
            "description" to desc,
            "creationDate" to year+"-01-01T05:00:00.000Z"
        )

        viewModel.crearArtista(JSONObject(postParams),
            { response ->
                var r: String = ""
                try {
                    // Display the first 500 characters of the response string.
                    var reslt: String = response.get("name").toString()
                    if(reslt == nombre){
                        r = "Artista creado correctamente"
                        mostrarMSJ(acti,r)
                        finish()
                    }else{
                        r = "Ha ocurrido un error, volver a intentar..."
                        mostrarMSJ(acti,r)
                    }
                }catch (ex:Exception){
                    r = "Ha ocurrido un error, volver a intentar..."
                    mostrarMSJ(acti,r)
                }
            },
            {
                Log.d("TAG", it.toString())
                var r: String =  "Ta ocurrido un error, volver a intentar..."
                mostrarMSJ(acti,r)
            })



        /*GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val call_funcion = async {ServiceRC().createBand(nombre, year, fav_song, gen, desc, img_basic)}
                val retorno = call_funcion.await()
                println(retorno)
                Log.d("Artistas", "resultado creacion:  ${retorno}")
                if(retorno.contains("OK")) {
                    runOnUiThread(Runnable {
                        mostrarMSJ(acti,"Artista creado correctamente")
                        finish()
                    })
                }else{
                    runOnUiThread(Runnable {
                        mostrarMSJ(acti,"Error creando Artista: ${retorno}")
                    })
                }
            }
        }*/
    }

    fun mostrarMSJ(acti: Activity, msj: String){
        runOnUiThread(Runnable {
            Toast.makeText(acti, msj, Toast.LENGTH_LONG).show()
        })
    }
}