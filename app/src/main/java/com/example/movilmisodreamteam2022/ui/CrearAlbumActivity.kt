package com.example.movilmisodreamteam2022.ui

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
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
import com.example.movilmisodreamteam2022.models.Album
import com.example.movilmisodreamteam2022.viewmodels.AlbumViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.system.measureTimeMillis

class CrearAlbumActivity : AppCompatActivity() {
    var cover_basic: String = "https://cdn.dribbble.com/users/1100029/screenshots/5950588/media/451c0eb8bb7675c9bea0ddc26efece44.png"


    lateinit var requestTest: EditText

    private lateinit var viewModel: AlbumViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_album)
        var parentLayout: View = findViewById(android.R.id.content)
        var EDNombreAlbum: EditText = findViewById<EditText>(R.id.EDNombreAlbum)
        var EDYearAlbum: EditText = findViewById<EditText>(R.id.EDYearAlbum)
        var EDArtista: EditText = findViewById<EditText>(R.id.EDArtista)
        var EDCancionPreferida: EditText = findViewById<EditText>(R.id.EDCancionPreferida)
        var EDGenero: EditText = findViewById<EditText>(R.id.EDGenero)
        var EDDescrip: EditText = findViewById<EditText>(R.id.EDDescrip)
        var EDRLabel: EditText = findViewById<EditText>(R.id.EDRLabel)
        var BtnCrearAlbum: Button = findViewById<Button>(R.id.BtnCrearAlbum)
        var action_Bar = getSupportActionBar()

        requestTest = findViewById<EditText>(R.id.lblRequestCrearAlbum)

        // showing the back button in action bar
        if (action_Bar != null) {
            action_Bar.setTitle("CREAR ALBUM")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }

        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(this.application)).get(AlbumViewModel::class.java)


        BtnCrearAlbum.setOnClickListener {

            var nombre:String = EDNombreAlbum.text.toString().trim()
            var year:String = EDYearAlbum.text.toString().trim()
            var desc:String = EDDescrip.text.toString().trim()
            var gen:String = EDGenero.text.toString().trim()
            var rlabel:String = EDRLabel.text.toString().trim()

            if(!nombre.isNullOrEmpty() && !year.isNullOrEmpty() && !desc.isNullOrEmpty() && !gen.isNullOrEmpty() && !rlabel.isNullOrEmpty()) {
                Snackbar.make(parentLayout, "CREANDO ALBUM...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                CrearAlbum_(this, nombre, year, desc, gen, rlabel)
            }else{
                Snackbar.make(parentLayout, "LOS VALORES NO PUEDEN SER VACIOS", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun CrearAlbum_(acti: Activity, name:String, year:String, desc:String, genre:String, recordLabel:String){

        val postParams = mapOf<String, Any>(
            "name" to name,
            "cover" to cover_basic,
            "releaseDate" to year+"-01-01T05:00:00.000Z",
            "description" to desc,
            "genre" to genre,
            "recordLabel" to recordLabel
        )

        viewModel.crearAlbum(JSONObject(postParams),
            { response ->
                var r: String = ""
                try {
                    // Display the first 500 characters of the response string.
                    var reslt: String = response.get("name").toString()
                    if(reslt == name){
                        r = "Album creado correctamente"
                        requestTest.setText(r)
                        mostrarMSJ(acti,r)
                        val handler = Handler()
                        handler.postDelayed(Runnable { finish() }, 2000)
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
    }

    fun mostrarMSJ(acti: Activity, msj: String){
        runOnUiThread(Runnable {
            Toast.makeText(acti, msj, Toast.LENGTH_LONG).show()

        })
    }
}