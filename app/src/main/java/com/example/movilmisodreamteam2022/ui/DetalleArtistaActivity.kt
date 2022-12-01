package com.example.movilmisodreamteam2022.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.ui.adapters.ArtistaAdapter
import com.example.movilmisodreamteam2022.viewmodels.ArtistaViewModel

class DetalleArtistaActivity : AppCompatActivity() {

    private lateinit var viewModel: ArtistaViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_artista)


        var action_Bar = getSupportActionBar()
        var parentLayout: View = findViewById(android.R.id.content)

        viewModel = ViewModelProvider(this, ArtistaViewModel.Factory(this.application)).get(ArtistaViewModel::class.java)

        var EDNombre: TextView = findViewById<TextView>(R.id.lblDetalleNombreTxt)
        var EDDesc: TextView = findViewById<TextView>(R.id.lblDetalleDescripcionTxt)
        var EDYear: TextView = findViewById<TextView>(R.id.lblDetalleFechaDeInicioTxt)
        val bundle = intent.extras
        val dato = bundle?.getString("id-Artista")

        if (action_Bar != null) {
            action_Bar.setTitle("Detalle del Artista")
            action_Bar.setDisplayHomeAsUpEnabled(true)
        }
        viewModel.getArtistaByIdFromNetwork(dato.toString())
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val nombreBanda = viewModel.artista.value?.name
                EDNombre.setText(nombreBanda.toString())

                val desBanda = viewModel.artista.value?.description
                EDDesc.setText(desBanda.toString())

                var releaseDateBanda = viewModel.artista.value?.creationDate
                releaseDateBanda = releaseDateBanda?.split("T")?.get(0)
                EDYear.setText(releaseDateBanda.toString())
            },
            2000 // value in milliseconds
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        //return super.onSupportNavigateUp()
        return true
    }
}