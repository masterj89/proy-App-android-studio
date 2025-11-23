package com.example.firstaplication


import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import db.DbHelper

class RegistrousuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro_usuario)

        val registrobtn = findViewById<Button>(R.id.registro_btn)

        registrobtn.setOnClickListener {
            val dbHelper = DbHelper(this)
            val db = dbHelper.writableDatabase

            if (db != null){
                Toast.makeText(this, "Base de datos creada", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_LONG).show()
            }
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrousuario)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}