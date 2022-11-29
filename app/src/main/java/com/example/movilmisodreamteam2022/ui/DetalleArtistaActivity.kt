package com.example.movilmisodreamteam2022.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.movilmisodreamteam2022.R

class DetalleArtistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_artista)
        var action_Bar = getSupportActionBar()
        var parentLayout: View = findViewById(android.R.id.content)


        var EDNombre: TextView = findViewById<TextView>(R.id.lblDetalleNombreTxt)
        var EDDesc: TextView = findViewById<TextView>(R.id.lblDetalleDescripcionTxt)
        var EDYear: TextView = findViewById<TextView>(R.id.lblDetalleFechaDeInicioTxt)

    }
}