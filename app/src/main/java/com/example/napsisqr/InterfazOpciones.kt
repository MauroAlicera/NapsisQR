package com.example.napsisqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InterfazOpciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfaz_opciones)
        val btnLectorTarjetas : Button = findViewById(R.id.btnLectorTarjetas)

        btnLectorTarjetas.setOnClickListener()
        {
            val i = Intent(this,lectorQR::class.java)
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        return
    }

}