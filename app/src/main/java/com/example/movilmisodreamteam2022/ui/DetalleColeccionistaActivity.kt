package com.example.movilmisodreamteam2022.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.viewmodels.ColeccionistaViewModel

class activity_detalle_collecionista : AppCompatActivity() {

    private lateinit var viewModel: ColeccionistaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_collecionista)
        var action_Bar = getSupportActionBar()
        var parentLayout: View = findViewById(android.R.id.content)

        viewModel = ViewModelProvider(this, ColeccionistaViewModel.Factory(this.application)).get(
            ColeccionistaViewModel::class.java)

        var EDNombre: TextView = findViewById<TextView>(R.id.lblDetalleColeccinistaNombreTxt)
        var EDEmail: TextView = findViewById<TextView>(R.id.lblDetalleColeccinistaEmailTxt)
        var EDCellPhone: TextView = findViewById<TextView>(R.id.lblDetalleColeccinistaTelefonoTxt)
        val bundle = intent.extras
        val dato = bundle?.getString("id-Coleccionista")

        if (action_Bar != null) {
            action_Bar.setTitle("Detalle del Coleccionista")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }
        viewModel.getColeccionistabyIdFromNetwork(dato.toString())
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val nombreBanda = viewModel.coleccionista.value?.name
                EDNombre.setText(nombreBanda.toString())

                val email = viewModel.coleccionista.value?.email
                EDEmail.setText(email.toString())

                val phone = viewModel.coleccionista.value?.telephone
                EDCellPhone.setText(phone.toString())
            },
            2000
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }
}