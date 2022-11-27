package com.example.movilmisodreamteam2022.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.viewmodels.ColeccionistaViewModel
import org.json.JSONObject


class CrearColeccionistaActivity : AppCompatActivity() {
    var img_basic: String = "https://cdn.dribbble.com/users/1100029/screenshots/5950588/media/451c0eb8bb7675c9bea0ddc26efece44.png"
    private lateinit var viewModel: ColeccionistaViewModel

    lateinit var requestTestColeccinista: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_coleccionista)
        var parentLayout: View = findViewById(android.R.id.content)
        var EDNombre: EditText = findViewById<EditText>(R.id.EDNombreColeccionista)
        var EDEmail: EditText = findViewById<EditText>(R.id.EDEmail)
        var EDcellphone: EditText = findViewById<EditText>(R.id.EDCellphoneColeccionista)
        var BtnCrearColeccionista: Button = findViewById<Button>(R.id.BtnCrearColeccionista)
        var action_Bar = getSupportActionBar()

        requestTestColeccinista = findViewById<EditText>(R.id.lblRequestCrearColeccinista)

        if (action_Bar != null) {
            action_Bar.setTitle("CREAR COLECCIONISTA")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }

        viewModel = ViewModelProvider(this, ColeccionistaViewModel.Factory(this.application)).get(ColeccionistaViewModel::class.java)

        BtnCrearColeccionista.setOnClickListener {

            var nombre:String = EDNombre.text.toString()
            var email: String = EDEmail.text.toString()
            var cellphone: String = EDcellphone.text.toString()

            if(!nombre.isNullOrEmpty() && !nombre.isNullOrEmpty() && !email.isNullOrEmpty() ) {
                Snackbar.make(parentLayout, "CREANDO COLECCIONISTA ...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                CrearColeccionista_(this, nombre, email, cellphone)
            }else{
                Snackbar.make(parentLayout, "Ninguno de los 3 campos entre el email, el nombre o el  número de celular puede ser vacio", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }

    fun CrearColeccionista_(acti: Activity, nombre: String, email: String, cellphone: String){

        val postParams = mapOf<String, Any>(
            "name" to nombre,
            "email" to email,
            "telephone" to cellphone
        )

        viewModel.crearColeccionista(JSONObject(postParams),
            { response ->
                var r: String = ""
                try {
                    // Display the first 500 characters of the response string.
                    var reslt: String = response.get("name").toString()
                    if(reslt == nombre){
                        r = "Coleccionista creado correctamente"
                        requestTestColeccinista.setText(r)
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